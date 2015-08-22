using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Kana.src.de.Kana.Util;
using Kana.src.de.Kana.Util.XML;

namespace Kana
{
    struct VocableCollection
    {
        static bool randomized = true;
        static LinkedList<Vocable> vocs;

        static VocableCollection()
        {
            WordGraph graph = new WordGraph();
            ImportXml.XmlToWordGraph("vocals.xml", out graph);
            vocs = graph.getContent();
        }

        static public List<LinkedList<String>> RawGerman
        {
            get
            {
                return (randomized) ?
              vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.DeWords })
                  .OrderBy(x => x.Index)
                  .Select(x => x.Value)
                  .ToList() :
              vocs.Select(x => x.DeWords)
                  .ToList();
            }
        }

        static public List<LinkedList<String>> RawEnglish
        {
            get
            {
                return (randomized) ?
                    vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.EnWords })
                        .OrderBy(x => x.Index)
                        .Select(x => x.Value)
                        .ToList() :
                    vocs.Select(x => x.EnWords)
                        .ToList();
            }
        }

        static public List<LinkedList<String>> RawKanji
        {
            get
            {
                return (randomized) ?
                    vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Kanji })
                        .OrderBy(x => x.Index)
                        .Select(x => x.Value)
                        .ToList() :
                    vocs.Select(x => x.Kanji)
                        .ToList(); ;
            }
        }

        static public List<String> RawRomaji
        {
            get
            {
                return (randomized) ?
                    vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Romaji })
                        .OrderBy(x => x.Index)
                        .Select(x => x.Value)
                        .ToList() :
                    vocs.Select(x => x.Romaji)
                        .ToList();
            }
        }

        static public List<List<Syllable>> getRawKana
        {
            get
            {
                return (randomized) ?
                    vocs.Select(x => new { Index = Guid.NewGuid(), Value = x.Word })
                        .OrderBy(x => x.Index)
                        .Select(x => x.Value)
                        .ToList() :
                    vocs.Select(x => x.Word)
                        .ToList();
            }
        }
    }
}
