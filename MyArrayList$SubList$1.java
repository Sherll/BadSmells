import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

class MyArrayList$SubList$1
  implements ListIterator<E>
{
  int cursor = this.val$index;
  int expectedModCount = MyArrayList.access$800(this.this$1.this$0);
  int lastRet = -1;
  
  MyArrayList$SubList$1(MyArrayList.SubList paramSubList, int paramInt1, int paramInt2) {}
  
  public void add(E paramE)
  {
    checkForComodification();
    try
    {
      int i = this.cursor;
      this.this$1.add(i, paramE);
      this.cursor = (i + 1);
      this.lastRet = -1;
      this.expectedModCount = MyArrayList.access$1100(this.this$1.this$0);
      return;
    }
    catch (IndexOutOfBoundsException paramE)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
  }
  
  final void checkForComodification()
  {
    if (this.expectedModCount == MyArrayList.access$1200(this.this$1.this$0)) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public void forEachRemaining(Consumer<? super E> paramConsumer)
  {
    paramConsumer.getClass();
    int j = this.this$1.size;
    int i = this.cursor;
    if (i >= j) {
      return;
    }
    Object[] arrayOfObject = this.this$1.this$0.elementData;
    if (this.val$offset + i < arrayOfObject.length)
    {
      while ((i != j) && (MyArrayList.SubList.access$900(this.this$1) == this.expectedModCount))
      {
        paramConsumer.accept(arrayOfObject[(this.val$offset + i)]);
        i += 1;
      }
      this.cursor = i;
      this.lastRet = i;
      checkForComodification();
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean hasNext()
  {
    return this.cursor != this.this$1.size;
  }
  
  public boolean hasPrevious()
  {
    return this.cursor != 0;
  }
  
  public E next()
  {
    checkForComodification();
    int i = this.cursor;
    if (i < this.this$1.size)
    {
      Object[] arrayOfObject = this.this$1.this$0.elementData;
      if (this.val$offset + i < arrayOfObject.length)
      {
        this.cursor = (i + 1);
        int j = this.val$offset;
        this.lastRet = i;
        return (E)arrayOfObject[(j + i)];
      }
      throw new ConcurrentModificationException();
    }
    throw new NoSuchElementException();
  }
  
  public int nextIndex()
  {
    return this.cursor;
  }
  
  public E previous()
  {
    checkForComodification();
    int i = this.cursor - 1;
    if (i >= 0)
    {
      Object[] arrayOfObject = this.this$1.this$0.elementData;
      if (this.val$offset + i < arrayOfObject.length)
      {
        this.cursor = i;
        int j = this.val$offset;
        this.lastRet = i;
        return (E)arrayOfObject[(j + i)];
      }
      throw new ConcurrentModificationException();
    }
    throw new NoSuchElementException();
  }
  
  public int previousIndex()
  {
    return this.cursor - 1;
  }
  
  public void remove()
  {
    if (this.lastRet >= 0) {
      checkForComodification();
    }
    try
    {
      this.this$1.remove(this.lastRet);
      this.cursor = this.lastRet;
      this.lastRet = -1;
      this.expectedModCount = MyArrayList.access$1000(this.this$1.this$0);
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
    throw new IllegalStateException();
  }
  
  public void set(E paramE)
  {
    if (this.lastRet >= 0) {
      checkForComodification();
    }
    try
    {
      this.this$1.this$0.set(this.val$offset + this.lastRet, paramE);
      return;
    }
    catch (IndexOutOfBoundsException paramE)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\MyArrayList$SubList$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */