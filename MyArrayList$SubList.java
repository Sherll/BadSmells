import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.function.Consumer;

class MyArrayList$SubList
  extends AbstractList<E>
  implements RandomAccess
{
  private final int offset;
  private final AbstractList<E> parent;
  private final int parentOffset;
  int size;
  
  MyArrayList$SubList(AbstractList<E> paramAbstractList, int paramInt1, int paramInt2, int paramInt3)
  {
    this.parent = paramInt1;
    this.parentOffset = paramInt3;
    this.offset = (paramInt2 + paramInt3);
    int i;
    this.size = (i - paramInt3);
    this.modCount = MyArrayList.access$700(paramAbstractList);
  }
  
  private void checkForComodification()
  {
    if (MyArrayList.access$1300(this.this$0) == this.modCount) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  private String outOfBoundsMsg(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Index: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(", Size: ");
    localStringBuilder.append(this.size);
    return localStringBuilder.toString();
  }
  
  private void rangeCheck(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.size)) {
      return;
    }
    throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
  }
  
  private void rangeCheckForAdd(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= this.size)) {
      return;
    }
    throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    return addAll(this.size, paramCollection);
  }
  
  public E get(int paramInt)
  {
    rangeCheck(paramInt);
    checkForComodification();
    return (E)this.this$0.elementData(this.offset + paramInt);
  }
  
  public Iterator<E> iterator()
  {
    return listIterator();
  }
  
  public ListIterator<E> listIterator(final int paramInt)
  {
    checkForComodification();
    rangeCheckForAdd(paramInt);
    new ListIterator()
    {
      int cursor = paramInt;
      int expectedModCount = MyArrayList.access$800(MyArrayList.SubList.this.this$0);
      int lastRet = -1;
      
      public void add(E paramAnonymousE)
      {
        checkForComodification();
        try
        {
          int i = this.cursor;
          MyArrayList.SubList.this.add(i, paramAnonymousE);
          this.cursor = (i + 1);
          this.lastRet = -1;
          this.expectedModCount = MyArrayList.access$1100(MyArrayList.SubList.this.this$0);
          return;
        }
        catch (IndexOutOfBoundsException paramAnonymousE)
        {
          for (;;) {}
        }
        throw new ConcurrentModificationException();
      }
      
      final void checkForComodification()
      {
        if (this.expectedModCount == MyArrayList.access$1200(MyArrayList.SubList.this.this$0)) {
          return;
        }
        throw new ConcurrentModificationException();
      }
      
      public void forEachRemaining(Consumer<? super E> paramAnonymousConsumer)
      {
        paramAnonymousConsumer.getClass();
        int j = MyArrayList.SubList.this.size;
        int i = this.cursor;
        if (i >= j) {
          return;
        }
        Object[] arrayOfObject = MyArrayList.SubList.this.this$0.elementData;
        if (this.val$offset + i < arrayOfObject.length)
        {
          while ((i != j) && (MyArrayList.SubList.access$900(MyArrayList.SubList.this) == this.expectedModCount))
          {
            paramAnonymousConsumer.accept(arrayOfObject[(this.val$offset + i)]);
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
        return this.cursor != MyArrayList.SubList.this.size;
      }
      
      public boolean hasPrevious()
      {
        return this.cursor != 0;
      }
      
      public E next()
      {
        checkForComodification();
        int i = this.cursor;
        if (i < MyArrayList.SubList.this.size)
        {
          Object[] arrayOfObject = MyArrayList.SubList.this.this$0.elementData;
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
          Object[] arrayOfObject = MyArrayList.SubList.this.this$0.elementData;
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
          MyArrayList.SubList.this.remove(this.lastRet);
          this.cursor = this.lastRet;
          this.lastRet = -1;
          this.expectedModCount = MyArrayList.access$1000(MyArrayList.SubList.this.this$0);
          return;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          for (;;) {}
        }
        throw new ConcurrentModificationException();
        throw new IllegalStateException();
      }
      
      public void set(E paramAnonymousE)
      {
        if (this.lastRet >= 0) {
          checkForComodification();
        }
        try
        {
          MyArrayList.SubList.this.this$0.set(this.val$offset + this.lastRet, paramAnonymousE);
          return;
        }
        catch (IndexOutOfBoundsException paramAnonymousE)
        {
          for (;;) {}
        }
        throw new ConcurrentModificationException();
        throw new IllegalStateException();
      }
    };
  }
  
  public E set(int paramInt, E paramE)
  {
    rangeCheck(paramInt);
    checkForComodification();
    Object localObject = this.this$0.elementData(this.offset + paramInt);
    this.this$0.elementData[(this.offset + paramInt)] = paramE;
    return (E)localObject;
  }
  
  public int size()
  {
    checkForComodification();
    return this.size;
  }
  
  public List<E> subList(int paramInt1, int paramInt2)
  {
    MyArrayList.subListRangeCheck(paramInt1, paramInt2, this.size);
    return new SubList(this.this$0, this, this.offset, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\MyArrayList$SubList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */