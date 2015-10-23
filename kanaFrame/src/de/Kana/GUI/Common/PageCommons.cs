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


    public abstract partial class MenuPage : KanaPage { }




    public abstract class ContentPage : KanaPage
    {
        protected Dictionary<string, string> currentSettings { get; set; }
        public abstract void ApplySettings(Dictionary<string, string> settings);
    }






    public abstract class SettingsPage : KanaPage
    {
        protected Dictionary<string, string> currentSettings { get; set; }

        protected abstract void ReadGUI();
        protected abstract void SetGUI();
        public abstract void DefaultSettings(bool apply);
        public abstract Dictionary<string, string> GetDefaultSettings();
    }






    public abstract class KanaPage : Page
    {
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
        public static bool Mode_Changed(Dictionary<string, string> oldS, Dictionary<string, string> newS)
        {
            if (newS == null | oldS == null) return true;
            return (String.Compare(oldS[MODE_KEY], newS[MODE_KEY]) != 0);
        }
    }









    public enum ESettings
    {

    }
}
