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

    public abstract class KanaPage : Page
    {
        public KanaPage() { }
        public virtual void HandleOnNavigate(KanaPage last) { }
        public virtual void HandleKeyDown(KeyEventArgs e) { }
    }

    public abstract partial class MenuPage : KanaPage
    {
        public MenuPage() { }
    }

    public abstract class ContentPage : KanaPage
    {
        public Settings Settings { get; }
        public ContentPage() { }
        public abstract void ApplySettings(Settings settings);
    }

    public abstract class SettingsPage : KanaPage
    {
        public SettingsPage() { }
        protected abstract void ReadGUI();
        protected abstract void SetGUI();
    }

    public interface INavigate
    {
        void Navigate(KanaPage page);
        void NavigateBack();
    }
}
