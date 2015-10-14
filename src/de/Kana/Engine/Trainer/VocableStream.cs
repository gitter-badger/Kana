using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Kana.src.de.Kana.Util;
using Kana.src.de.Kana.Util.XML;

namespace Kana
{
    struct VocableStream
    {
        public enum Type
        {
            German, English, Kanji, Romaji, Kana
        }

        static LinkedList<Vocable> vocs;
        static Dictionary<string, ICollection> streamCollection;

        private LinkedList<string> stream;
        private Type currentStreamType;

        static VocableStream()
        {
            WordGraph graph = new WordGraph();
            ImportXml.XmlToWordGraph("vocals.xml", out graph);
            vocs = graph.getContent();

            streamCollection = new Dictionary<string, ICollection>();
            streamCollection.Add("German",
                vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.DeWords })
                    .OrderBy(x => x.Index)
                    .Select(x => x.Value)
                    .ToList());
            streamCollection.Add("English",
                vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.EnWords })
                    .OrderBy(x => x.Index)
                    .Select(x => x.Value)
                    .ToList());
            streamCollection.Add("Kanji",
                vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Kanji })
                    .OrderBy(x => x.Index)
                    .Select(x => x.Value)
                    .ToList());
            streamCollection.Add("Romaji",
                vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Romaji })
                    .OrderBy(x => x.Index)
                    .Select(x => x.Value)
                    .ToList());
            streamCollection.Add("Kana",
                vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Word })
                    .OrderBy(x => x.Index)
                    .Select(x => x.Value)
                    .ToList());
        }
        
        public void InitializeStream(Type type)
        {
            currentStreamType = type;
            stream = new LinkedList<string>();
            foreach (var str in streamCollection[type.ToString()])
            {

                if (str is ICollection)
                {
                    if (currentStreamType == Type.Kanji) {
                        string tmp = "";
                        foreach (var s in str as ICollection) {
                            tmp += s;
                        }
                        stream.AddLast(tmp);
                    } else {
                        stream.AddLast(((LinkedList<string>)str)
                            .Select(x => new { Index = Guid.NewGuid(), Value = x})
                            .OrderBy(x => x.Index).Select(x => x.Value)
                            .ToList()
                            .First());
                    }
                }
                else
                {
                    stream.AddLast(str.ToString());
                }
            }
        }

        public string Next() {
            if (stream.Count == 0) InitializeStream(currentStreamType);
            string tmp = stream.First.Value;
            stream.RemoveFirst();
            return tmp;
        }
    }
}
