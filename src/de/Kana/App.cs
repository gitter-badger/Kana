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
        public static void Main(string[] args)
        {
            DebugForm debugWindow = new DebugForm();
            List<LinkedList<string>> test = VocableCollection.RawGerman;
            Application.EnableVisualStyles();
            Application.Run(debugWindow);
        }
    }
}

