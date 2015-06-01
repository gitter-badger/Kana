using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kana.src.de.Kana.Util
{
    interface IKanaDictionary
    {
        void Add(Vocable word);
        List<Vocable> getContent();
    }
}
