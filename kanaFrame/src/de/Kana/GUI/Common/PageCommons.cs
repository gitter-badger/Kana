using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;

namespace KanaFrame
{
    public interface IMenuPage
    { }

    public interface IContentPage
    {
        void applySettings(Dictionary<String, String> settings);
    }

    public interface ISettingsPage
    {
        void setSettings();
        void revertContent();
    }

    public interface IPage
    {
        void HandleOnNavigate();
    }

    public class KanaPage : Page, IPage
    {
        public virtual void HandleOnNavigate()
        { }

        public virtual void HandleKeyDown(KeyEventArgs e)
        { }
    }

    public class Settings
    {
        public const string MODE_KEY = "MODE";

        public static bool Mode_Changed(Dictionary<String, String> oldS, Dictionary<String, String> newS)
        {
            if (newS == null | oldS == null) return true;
            return (!oldS[MODE_KEY].Equals(newS[MODE_KEY]));
        }
    }

    public enum ESettings
    {

    }
}
