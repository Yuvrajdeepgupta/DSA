link - https://youtu.be/81h8O-0U5oo?si=k2ZaGJkajvYcjUau

class Node    // Node class 
{
    int value;
    int key;
    Node next;
    Node prev;
    public Node(int key,int value)
    {
        this.key=key;
        this.value=value;
        this.next=null;
        this.prev=null;
    }
}
class LRUCache
{
    int capacity;       //storing capacity
    int size=0;           //ll size

    HashMap<Integer,Node> map=new HashMap<>();
    Node head;
    Node tail;

    public LRUCache(int capacity) 
    {
        this.capacity=capacity;
        this.head=null;
        this.tail=null;
    }
    
    public int get(int key) 
    {
        if(map.containsKey(key))
        {
            Node temp=map.get(key);

            if(temp!=tail)
            {
                RemoveBinding(temp);
                AddLast(temp);
            }
            return temp.value;
        }
        else
        {
            return -1;
        }
    }
    
    public void put(int key, int value) 
    {
        if(map.containsKey(key))
        {
            Node temp=map.get(key);

            if(temp!=tail)
            {
                RemoveBinding(temp);
                AddLast(temp);
            }
            temp.value=value;
            map.put(key,temp);
        }
        else
        {
            Node newnode=new Node(key,value);

            if(size<capacity)
            {
                AddLast(newnode);
            }
            else
            {
                RemoveFirst();
                AddLast(newnode);
            }
            size++;
            map.put(key,newnode);
        }   
    }

    public void AddLast(Node newnode)
    {
        if(head==null && tail==null)
        {
            head=newnode;
            tail=newnode;
        }
        else
        {
            tail.next=newnode;
            newnode.prev=tail;
            tail=newnode;
        }
    }

    public void RemoveFirst()
    {
        map.remove(head.key);
        head=head.next;
        if(head==null)
        {
            tail=null;
        }
        else
        {
            head.prev=null;
        }
        size--;
    }

    public void RemoveBinding(Node temp)
    {
        if(temp==head)
        {
            head=head.next;
            if(head==null)
            {
                tail=null;
            }
            else
            {
                head.prev=null;
            }
        }
        else
        {
            temp.prev.next=temp.next;
            temp.next.prev=temp.prev;
        }
        temp.next=null;
        temp.prev=null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */