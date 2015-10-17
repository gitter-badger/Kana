using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public interface IMenuPageBase : IPage
    { }

    public interface IMainPageBase : IPage
    {
        void applySettings(Dictionary<String, String> settings);
    }

    public interface ISettingsPageBase : IPage
    {
        void setSettings();
        void revertContent();
    }

    public interface IPage
    {
        void onNavigate(IPage page);
    }

    public class Settings
    {
        public const string MODE_KEY = "MODE";
    }

    public enum ESettings
    {

    }
}
