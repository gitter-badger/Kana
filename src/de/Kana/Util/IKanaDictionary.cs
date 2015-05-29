using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kana.src.de.Kana.Util
{
    interface IKanaDictionary
    {
        void Add(Vocable word, List<string> kanji, List<string> enTrans, List<string> deTrans, params Enum[] flags);
        LinkedList<Vocable> getContent();
    }
}
