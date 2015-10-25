using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace KanaFrame
{

    public interface IContentControl { }


    public abstract partial class MenuPage : KanaPage
    {
        public MenuPage() { }
    }




    public abstract class ContentPage : KanaPage
    {
        public ContentPage() { }
        protected Dictionary<string, string> currentSettings { get; set; }
        public abstract void ApplySettings(Dictionary<string, string> settings);
    }






    public abstract class SettingsPage : KanaPage
    {
        public SettingsPage() { }
        protected Dictionary<string, string> currentSettings { get; set; }
        protected abstract void ReadGUI();
        protected abstract void SetGUI();
        public abstract void DefaultSettings(bool apply);
        public abstract Dictionary<string, string> GetDefaultSettings();
    }






    public abstract class KanaPage : Page
    {
        public KanaPage() { }
        public virtual void HandleOnNavigate(KanaPage last) { }
        public virtual void HandleKeyDown(KeyEventArgs e) { }
    }



    public interface INavigate
    {
        void Navigate(KanaPage page);
        void NavigateBack();
    }





    public class Settings
    {
        public const string MODE_KEY = "MODE";
        public const string OPTION_TRUE = "TRUE";
        public const string OPTION_FALSE = "FALSE";
        public static bool Mode_Changed(Dictionary<string, string> oldS, Dictionary<string, string> newS)
        {
            if (newS == null | oldS == null) return true;
            return (String.Compare(oldS[MODE_KEY], newS[MODE_KEY]) != 0);
        }

        public static bool ToBool(string str)
        {
            return (String.Compare(str, OPTION_TRUE) == 0);
        }
    }









    public enum ESettings
    {

    }
}
