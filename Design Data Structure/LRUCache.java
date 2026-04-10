
//Brute Force get and put funtion taking O(n) time 
//In this approach the most expensive operation is removing the element. So we need to think of DS in which remove the element takes O(1) time
class LRUCache {
    List<int[]> cache;
    int capacity;

    public LRUCache(int capacity) {
        cache = new ArrayList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i)[0] == key) {
                int value = cache.get(i)[1];

                int[] temp = cache.get(i);
                cache.remove(i);
                cache.add(temp); // move to most recent

                return value;
            }
        }

        return -1;
    }

    public void put(int key, int value) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i)[0] == key) {
                cache.remove(i);
                cache.add(new int[] { key, value });
                return;
            }
        }

        if (cache.size() == capacity)
            cache.remove(0);

        cache.add(new int[] { key, value });
    }
}

// Optimised Approach: Using DLL + Hashmap