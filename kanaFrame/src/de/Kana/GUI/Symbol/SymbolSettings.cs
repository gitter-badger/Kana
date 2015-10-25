using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public class SymbolSettings : Settings
    {
        public static readonly string MODE_MATCH = "MATCH";
        public static readonly string MODE_FLOW = "FLOW";
        public static readonly string KEY_MARKS = "MARKS";
        public static readonly string KEY_LEARNING = "LEARNING";

        public new SymbolSettings Clone()
        {
            SymbolSettings result = new SymbolSettings();
            result[KEY_MODE] = this[KEY_MODE];
            result[KEY_MARKS] = this[KEY_MARKS];
            result[KEY_LEARNING] = this[KEY_LEARNING];
            return result;
        }

        public override void InitSettings()
        {
            this[KEY_MODE] = MODE_MATCH;
            this[KEY_LEARNING] = OPTION_TRUE;
            this[KEY_MARKS] = OPTION_FALSE;
        }
    }
}