import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class MyArrayList$ListItr
  extends MyArrayList<E>.Itr
  implements ListIterator<E>
{
  MyArrayList$ListItr(MyArrayList paramMyArrayList, int paramInt)
  {
    super(paramMyArrayList, null);
    this.cursor = paramInt;
  }
  
  public void add(E paramE)
  {
    checkForComodification();
    try
    {
      int i = this.cursor;
      this.this$0.add(i, paramE);
      this.cursor = (i + 1);
      this.lastRet = -1;
      this.expectedModCount = MyArrayList.access$600(this.this$0);
      return;
    }
    catch (IndexOutOfBoundsException paramE)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean hasPrevious()
  {
    return this.cursor != 0;
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
      Object[] arrayOfObject = this.this$0.elementData;
      if (i < arrayOfObject.length)
      {
        this.cursor = i;
        this.lastRet = i;
        return (E)arrayOfObject[i];
      }
      throw new ConcurrentModificationException();
    }
    throw new NoSuchElementException();
  }
  
  public int previousIndex()
  {
    return this.cursor - 1;
  }
  
  public void set(E paramE)
  {
    if (this.lastRet >= 0) {
      checkForComodification();
    }
    try
    {
      this.this$0.set(this.lastRet, paramE);
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


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\MyArrayList$ListItr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */