using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kana {
    class Matcher {
        public static bool Matches(string toCheck, string reference) {
            return toCheck.Equals(reference);
        }
    }
}
