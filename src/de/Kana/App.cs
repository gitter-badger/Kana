using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Kana.src.de.Kana.GUI;
using Kana.src.de.Kana.Util;
using Kana.src.de.Kana.Util.XML;

namespace Kana
{
	public class App
	{
		public static void Main(string[] args) {
            DebugForm debugWindow = new DebugForm();

            WordGraph graph = new WordGraph();

            ImportXml.XmlToWordGraph("vocals.xml", out graph);

            foreach (Vocable v in graph.getContent())
                System.Console.WriteLine(v);

            debugWindow.showItems(graph.getContent());


            Application.EnableVisualStyles();
            Application.Run(debugWindow);
        }
	}
}

