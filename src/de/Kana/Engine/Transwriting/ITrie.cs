using System.Collections.Generic;

namespace Kana.Transwriting
{
    public interface ITrie<TValue>
    {
        IEnumerable<TValue> Retrieve(string query);
        void Add(string key, TValue value);
    }
}