using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public interface IMainPageBase
    {
        void applySettings(Dictionary<String, String> settings);
    }

    public interface ISettingsPageBase
    {
        void setSettings();
        void revertContent();
    }
}
