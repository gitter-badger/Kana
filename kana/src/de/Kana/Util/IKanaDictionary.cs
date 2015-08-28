using System.Collections.Generic;

namespace Kana.src.de.Kana.Util {
    interface IKanaDictionary {
        void Add(Vocable word);
        List<Vocable> getContent();
    }
}
