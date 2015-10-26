using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public class VocabularySettings : Settings
    {
        public static readonly string KEY_1 = "1";
        public static readonly string KEY_2 = "2";
        public static readonly string KEY_3 = "3";
        public static readonly string OPTION_1_FIRST = "FIRST";
        public static readonly string OPTION_1_SECOND = "SECOND";

        public new VocabularySettings Clone()
        {
            VocabularySettings result = new VocabularySettings();
            result[KEY_1] = this[KEY_1];
            result[KEY_2] = this[KEY_2];
            result[KEY_3] = this[KEY_3];
            return result;
        }

        public override void InitSettings()
        {
            this[KEY_1] = OPTION_1_FIRST;
            this[KEY_2] = OPTION_TRUE;
            this[KEY_3] = "Peter Parker";
        }
    }
}