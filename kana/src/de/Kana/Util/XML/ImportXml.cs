using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using System.Xml;

namespace Kana.src.de.Kana.Util.XML {
    class ImportXml {
        public static bool XmlToWordGraph(string pathToXml, out Dictionary<WordGraph.Type, WordGraph> graphs) {
            string exeLoc = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            try {
                XmlTextReader reader = new XmlTextReader(Path.Combine(exeLoc, "..\\..\\xml\\vocals.xml"));
                Vocable voc = default (Vocable);
                Alphabet type = default (Alphabet);
                graphs = new Dictionary<WordGraph.Type, WordGraph>();
                graphs[WordGraph.Type.Japanese] = new WordGraph();
                graphs[WordGraph.Type.German] = new WordGraph();
                graphs[WordGraph.Type.English] = new WordGraph();
                while (reader.Read()) {
                    if (reader.NodeType == XmlNodeType.Element) {
                        switch (reader.Name) {
                            case "Vocal":
                                voc = new Vocable(null);
                                continue;
                            case "Romaji":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "")
                                        voc.Romaji = reader.Value.Trim();
                                continue;
                            case "Type":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "") {
                                        type = (Alphabet)int.Parse(reader.Value.Trim());
                                    }
                                continue;
                            case "Japanese":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "")
                                        foreach (char s in reader.Value.Trim())
                                            voc.Word.Add(new Syllable(""+s, type));
                                continue;
                            case "Kanji":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "")
                                        foreach (string obj in reader.Value.Split('\n'))
                                            if (!obj.Trim().Equals(""))
                                                voc.Kanji.AddLast(obj.Trim());
                                continue;
                            case "English":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "")
                                        foreach (string obj in reader.Value.Split('\n'))
                                            if (!obj.Trim().Equals(""))
                                                voc.EnWords.AddLast(obj.Trim());
                                continue;
                            case "German":
                                if (reader.Read())
                                    if (reader.NodeType == XmlNodeType.Text && reader.Value.Trim() != "")
                                        foreach (string obj in reader.Value.Split('\n'))
                                            if (!obj.Trim().Equals(""))
                                                voc.DeWords.AddLast(obj.Trim());
                                continue;
                            default:
                                continue;
                        }
                    }
                    if (reader.NodeType == XmlNodeType.EndElement && reader.Name == "Vocal") {
                        graphs[WordGraph.Type.Japanese].Add(voc, voc.ToString());
                        foreach (string word in voc.DeWords)
                            graphs[WordGraph.Type.German].Add(voc, word);
                        foreach (string word in voc.EnWords)
                            graphs[WordGraph.Type.English].Add(voc, word);
                    }
                }
            } catch (FileNotFoundException ex) {
                Console.WriteLine(ex.Message);
                graphs = null;
                return false;
            }
            return true;
        }
    }
}
