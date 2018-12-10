import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

class MyArrayList$Itr
  implements Iterator<E>
{
  int cursor;
  int expectedModCount = MyArrayList.access$100(this.this$0);
  int lastRet = -1;
  
  private MyArrayList$Itr(MyArrayList paramMyArrayList) {}
  
  final void checkForComodification()
  {
    if (MyArrayList.access$500(this.this$0) == this.expectedModCount) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public void forEachRemaining(Consumer<? super E> paramConsumer)
  {
    paramConsumer.getClass();
    int j = MyArrayList.access$200(this.this$0);
    int i = this.cursor;
    if (i >= j) {
      return;
    }
    Object[] arrayOfObject = this.this$0.elementData;
    if (i < arrayOfObject.length)
    {
      while ((i != j) && (MyArrayList.access$400(this.this$0) == this.expectedModCount))
      {
        paramConsumer.accept(arrayOfObject[i]);
        i += 1;
      }
      this.cursor = i;
      this.lastRet = (i - 1);
      checkForComodification();
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean hasNext()
  {
    return this.cursor != MyArrayList.access$200(this.this$0);
  }
  
  public E next()
  {
    checkForComodification();
    int i = this.cursor;
    if (i < MyArrayList.access$200(this.this$0))
    {
      Object[] arrayOfObject = this.this$0.elementData;
      if (i < arrayOfObject.length)
      {
        this.cursor = (i + 1);
        this.lastRet = i;
        return (E)arrayOfObject[i];
      }
      throw new ConcurrentModificationException();
    }
    throw new NoSuchElementException();
  }
  
  public void remove()
  {
    if (this.lastRet >= 0) {
      checkForComodification();
    }
    try
    {
      this.this$0.remove(this.lastRet);
      this.cursor = this.lastRet;
      this.lastRet = -1;
      this.expectedModCount = MyArrayList.access$300(this.this$0);
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      for (;;) {}
    }
    throw new ConcurrentModificationException();
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\MyArrayList$Itr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */