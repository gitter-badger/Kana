using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;

namespace KanaFrame
{
    public class Progress
    {
        public event EventHandler OnChange;
        public HiraSyllable Symbol { get; protected set; }

        private bool enabled;
        public bool Enabled { get { return enabled; } set { enabled = value; Changed(); } }

        private int streak;
        public int Streak { get { return streak; } private set { streak = value; Changed(); } }

        public Progress(HiraSyllable symbol)
        {
            Symbol = symbol;
            streak = 0; //This is not a typo. And F off if you are to say "that ain't correct OOP".
        }

        public void Enable()
        {
            Enabled = true;
        }

        public void Disable()
        {
            Enabled = false;
        }

        public void Reset()
        {
            Streak = 0;
        }

        public void Correct()
        {
            Streak = Math.Max(0, Streak + 1);
        }

        public void Wrong()
        {
            Streak = Math.Min(0, Streak - 1);
        }

        private void Changed()
        {
            if (OnChange != null) OnChange(this, new EventArgs());
        }

        public Color ToColor()
        {
            Color result;
            if (!Enabled) result = Color.FromArgb(0, 0, 0, 0);
            else
            {
                if (Streak == 0) result = Color.FromRgb(255, 255, 100);
                else if (Streak > 2) result = Color.FromRgb(100, 100, 255);
                else if (Streak > 0) result = Color.FromRgb(100, 255, 100);
                else result = Color.FromRgb(255, 100, 100);
            }
            return result;
        }
    }//END class SymbolProgress



    public class UserProgress
    {
        private Dictionary<string, Progress> progress { get; set; }
        public IEnumerable<Progress> Progress { get { return progress.Values; } }
        public Progress this[string key] { get { return progress[key]; } private set { progress[key] = value; } }

        public UserProgress(IEnumerable<HiraSyllable> pool)
        {
            progress = new Dictionary<string, Progress>();
            foreach (HiraSyllable syllable in pool)
            {
                progress[syllable.Characters] = new Progress(syllable);
            }
        }
    }//END class UserProgress
}//END namespace
