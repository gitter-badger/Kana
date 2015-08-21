using System.Collections.Generic;

namespace Kana.Transwriting
{
    public interface ITrie
    {
        IEnumerable<string> Retrieve(string query);
        void Add(string key, string value);
        string Replace(string query);
    }
}