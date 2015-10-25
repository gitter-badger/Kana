using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public class Settings
    {
        public static readonly string KEY_MODE = "MODE";
        public static readonly string OPTION_TRUE = "TRUE";
        public static readonly string OPTION_FALSE = "FALSE";
        public static readonly string OPTION_NULL = "NULL";

        protected Dictionary<string, string> Options { get; private set; }

        public string this[string key] { get { return Options[key]; } set { Options[key] = value; } }

        public Settings()
        {
            Options = new Dictionary<string, string>();
            InitSettings();
        }

        public virtual void InitSettings()
        {
            this[KEY_MODE] = OPTION_NULL;
        }

        public virtual Settings Clone()
        {
            Settings result = new Settings();
            result[KEY_MODE] = Options[KEY_MODE];
            return result;
        }

        public bool Equals(string key, string value)
        {
            return String.Compare(this[key], value) == 0;
        }

        public bool Bool(String key)
        {
            return String.Compare(this[key], OPTION_TRUE) == 0;
        }

        public void Bool(String key, bool value)
        {
            this[key] = value ? OPTION_TRUE : OPTION_FALSE;
        }

        public int Int(String key)
        {
            return Int32.Parse(this[key]);
        }

        public void Int(String key, int value)
        {
            this[key] = Convert.ToString(value);
        }
    }
}