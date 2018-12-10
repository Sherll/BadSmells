import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.function.Consumer;
import sun.misc.JavaOISAccess;
import sun.misc.SharedSecrets;

public class MyArrayList<E>
  extends AbstractList<E>
  implements List<E>, RandomAccess, Cloneable, Serializable
{
  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
  private static final int MAX_ARRAY_SIZE = 2147483639;
  private static final long serialVersionUID = 8683452581122892189L;
  transient Object[] elementData;
  private int size;
  
  public MyArrayList()
  {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }
  
  public MyArrayList(int paramInt)
  {
    if (paramInt > 0)
    {
      this.elementData = new Object[paramInt];
      return;
    }
    if (paramInt == 0)
    {
      this.elementData = EMPTY_ELEMENTDATA;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Illegal Capacity: ");
    localStringBuilder.append(paramInt);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public MyArrayList(Collection<? extends E> paramCollection)
  {
    this.elementData = paramCollection.toArray();
    int i = this.elementData.length;
    this.size = i;
    if (i != 0)
    {
      if (this.elementData.getClass() != Object[].class) {
        this.elementData = Arrays.copyOf(this.elementData, this.size, Object[].class);
      }
    }
    else {
      this.elementData = EMPTY_ELEMENTDATA;
    }
  }
  
  /* Error */
  private boolean batchRemove(Collection<?> paramCollection, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 54	MyArrayList:elementData	[Ljava/lang/Object;
    //   4: astore 8
    //   6: iconst_0
    //   7: istore 6
    //   9: iconst_0
    //   10: istore 5
    //   12: iconst_0
    //   13: istore_3
    //   14: iload 5
    //   16: aload_0
    //   17: getfield 85	MyArrayList:size	I
    //   20: if_icmpge +54 -> 74
    //   23: aload_1
    //   24: aload 8
    //   26: iload 5
    //   28: aaload
    //   29: invokeinterface 120 2 0
    //   34: istore 7
    //   36: iload_3
    //   37: istore 4
    //   39: iload 7
    //   41: iload_2
    //   42: if_icmpne +20 -> 62
    //   45: aload 8
    //   47: iload_3
    //   48: aload 8
    //   50: iload 5
    //   52: aaload
    //   53: aastore
    //   54: iload_3
    //   55: iconst_1
    //   56: iadd
    //   57: istore 4
    //   59: goto +3 -> 62
    //   62: iload 5
    //   64: iconst_1
    //   65: iadd
    //   66: istore 5
    //   68: iload 4
    //   70: istore_3
    //   71: goto -57 -> 14
    //   74: iload_3
    //   75: istore 4
    //   77: iload 5
    //   79: aload_0
    //   80: getfield 85	MyArrayList:size	I
    //   83: if_icmpeq +31 -> 114
    //   86: aload 8
    //   88: iload 5
    //   90: aload 8
    //   92: iload_3
    //   93: aload_0
    //   94: getfield 85	MyArrayList:size	I
    //   97: iload 5
    //   99: isub
    //   100: invokestatic 126	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   103: iload_3
    //   104: aload_0
    //   105: getfield 85	MyArrayList:size	I
    //   108: iload 5
    //   110: isub
    //   111: iadd
    //   112: istore 4
    //   114: iload 6
    //   116: istore_2
    //   117: iload 4
    //   119: aload_0
    //   120: getfield 85	MyArrayList:size	I
    //   123: if_icmpeq +50 -> 173
    //   126: iload 4
    //   128: istore_3
    //   129: iload_3
    //   130: aload_0
    //   131: getfield 85	MyArrayList:size	I
    //   134: if_icmpge +15 -> 149
    //   137: aload 8
    //   139: iload_3
    //   140: aconst_null
    //   141: aastore
    //   142: iload_3
    //   143: iconst_1
    //   144: iadd
    //   145: istore_3
    //   146: goto -17 -> 129
    //   149: aload_0
    //   150: aload_0
    //   151: getfield 103	java/util/AbstractList:modCount	I
    //   154: aload_0
    //   155: getfield 85	MyArrayList:size	I
    //   158: iload 4
    //   160: isub
    //   161: iadd
    //   162: putfield 103	java/util/AbstractList:modCount	I
    //   165: aload_0
    //   166: iload 4
    //   168: putfield 85	MyArrayList:size	I
    //   171: iconst_1
    //   172: istore_2
    //   173: iload_2
    //   174: ireturn
    //   175: astore_1
    //   176: iload_3
    //   177: istore 4
    //   179: iload 5
    //   181: aload_0
    //   182: getfield 85	MyArrayList:size	I
    //   185: if_icmpeq +31 -> 216
    //   188: aload 8
    //   190: iload 5
    //   192: aload 8
    //   194: iload_3
    //   195: aload_0
    //   196: getfield 85	MyArrayList:size	I
    //   199: iload 5
    //   201: isub
    //   202: invokestatic 126	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   205: iload_3
    //   206: aload_0
    //   207: getfield 85	MyArrayList:size	I
    //   210: iload 5
    //   212: isub
    //   213: iadd
    //   214: istore 4
    //   216: iload 4
    //   218: aload_0
    //   219: getfield 85	MyArrayList:size	I
    //   222: if_icmpeq +48 -> 270
    //   225: iload 4
    //   227: istore_3
    //   228: iload_3
    //   229: aload_0
    //   230: getfield 85	MyArrayList:size	I
    //   233: if_icmpge +15 -> 248
    //   236: aload 8
    //   238: iload_3
    //   239: aconst_null
    //   240: aastore
    //   241: iload_3
    //   242: iconst_1
    //   243: iadd
    //   244: istore_3
    //   245: goto -17 -> 228
    //   248: aload_0
    //   249: aload_0
    //   250: getfield 103	java/util/AbstractList:modCount	I
    //   253: aload_0
    //   254: getfield 85	MyArrayList:size	I
    //   257: iload 4
    //   259: isub
    //   260: iadd
    //   261: putfield 103	java/util/AbstractList:modCount	I
    //   264: aload_0
    //   265: iload 4
    //   267: putfield 85	MyArrayList:size	I
    //   270: aload_1
    //   271: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	this	MyArrayList
    //   0	272	1	paramCollection	Collection<?>
    //   0	272	2	paramBoolean	boolean
    //   13	232	3	i	int
    //   37	229	4	j	int
    //   10	203	5	k	int
    //   7	108	6	bool1	boolean
    //   34	9	7	bool2	boolean
    //   4	233	8	arrayOfObject	Object[]
    // Exception table:
    //   from	to	target	type
    //   14	36	175	finally
  }
  
  private static int calculateCapacity(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfObject == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      return Math.max(10, paramInt);
    }
    return paramInt;
  }
  
  private void ensureCapacityInternal(int paramInt)
  {
    ensureExplicitCapacity(calculateCapacity(this.elementData, paramInt));
  }
  
  private void ensureExplicitCapacity(int paramInt)
  {
    this.modCount += 1;
    if (paramInt - this.elementData.length > 0) {
      grow(paramInt);
    }
  }
  
  private void fastRemove(int paramInt)
  {
    this.modCount += 1;
    int i = this.size - paramInt - 1;
    if (i > 0) {
      System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
    }
    Object[] arrayOfObject = this.elementData;
    paramInt = this.size - 1;
    this.size = paramInt;
    arrayOfObject[paramInt] = null;
  }
  
  private void grow(int paramInt)
  {
    int i = this.elementData.length;
    int j = i + (i >> 1);
    i = j;
    if (j - paramInt < 0) {
      i = paramInt;
    }
    j = i;
    if (i - 2147483639 > 0) {
      j = hugeCapacity(paramInt);
    }
    this.elementData = Arrays.copyOf(this.elementData, j);
  }
  
  private static int hugeCapacity(int paramInt)
  {
    if (paramInt >= 0)
    {
      int i = 2147483639;
      if (paramInt > 2147483639) {
        i = Integer.MAX_VALUE;
      }
      return i;
    }
    throw new OutOfMemoryError();
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
    if (paramInt < this.size) {
      return;
    }
    throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
  }
  
  private void rangeCheckForAdd(int paramInt)
  {
    if ((paramInt <= this.size) && (paramInt >= 0)) {
      return;
    }
    throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.elementData = EMPTY_ELEMENTDATA;
    paramObjectInputStream.defaultReadObject();
    paramObjectInputStream.readInt();
    if (this.size > 0)
    {
      int i = calculateCapacity(this.elementData, this.size);
      SharedSecrets.getJavaOISAccess().checkArray(paramObjectInputStream, Object[].class, i);
      ensureCapacityInternal(this.size);
      Object[] arrayOfObject = this.elementData;
      i = 0;
      while (i < this.size)
      {
        arrayOfObject[i] = paramObjectInputStream.readObject();
        i += 1;
      }
    }
  }
  
  static void subListRangeCheck(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt2 <= paramInt3)
      {
        if (paramInt1 <= paramInt2) {
          return;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("fromIndex(");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(") > toIndex(");
        localStringBuilder.append(paramInt2);
        localStringBuilder.append(")");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("toIndex = ");
      localStringBuilder.append(paramInt2);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("fromIndex = ");
    localStringBuilder.append(paramInt1);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    int j = this.modCount;
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeInt(this.size);
    int i = 0;
    while (i < this.size)
    {
      paramObjectOutputStream.writeObject(this.elementData[i]);
      i += 1;
    }
    if (this.modCount == j) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public void add(int paramInt, E paramE)
  {
    rangeCheckForAdd(paramInt);
    ensureCapacityInternal(this.size + 1);
    System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + 1, this.size - paramInt);
    this.elementData[paramInt] = paramE;
    this.size += 1;
  }
  
  public boolean add(E paramE)
  {
    ensureCapacityInternal(this.size + 1);
    Object[] arrayOfObject = this.elementData;
    int i = this.size;
    this.size = (i + 1);
    arrayOfObject[i] = paramE;
    return true;
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection)
  {
    rangeCheckForAdd(paramInt);
    paramCollection = paramCollection.toArray();
    int i = paramCollection.length;
    ensureCapacityInternal(this.size + i);
    int j = this.size - paramInt;
    if (j > 0) {
      System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + i, j);
    }
    Object[] arrayOfObject = this.elementData;
    boolean bool = false;
    System.arraycopy(paramCollection, 0, arrayOfObject, paramInt, i);
    this.size += i;
    if (i != 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    paramCollection = paramCollection.toArray();
    int i = paramCollection.length;
    ensureCapacityInternal(this.size + i);
    Object[] arrayOfObject = this.elementData;
    int j = this.size;
    boolean bool = false;
    System.arraycopy(paramCollection, 0, arrayOfObject, j, i);
    this.size += i;
    if (i != 0) {
      bool = true;
    }
    return bool;
  }
  
  public void clear()
  {
    this.modCount += 1;
    int i = 0;
    while (i < this.size)
    {
      this.elementData[i] = null;
      i += 1;
    }
    this.size = 0;
  }
  
  public Object clone()
  {
    try
    {
      MyArrayList localMyArrayList = (MyArrayList)super.clone();
      localMyArrayList.elementData = Arrays.copyOf(this.elementData, this.size);
      localMyArrayList.modCount = 0;
      return localMyArrayList;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError(localCloneNotSupportedException);
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return indexOf(paramObject) >= 0;
  }
  
  E elementData(int paramInt)
  {
    return (E)this.elementData[paramInt];
  }
  
  public void ensureCapacity(int paramInt)
  {
    int i;
    if (this.elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      i = 0;
    } else {
      i = 10;
    }
    if (paramInt > i) {
      ensureExplicitCapacity(paramInt);
    }
  }
  
  public void forEach(Consumer<? super E> paramConsumer)
  {
    paramConsumer.getClass();
    int j = this.modCount;
    Object[] arrayOfObject = (Object[])this.elementData;
    int k = this.size;
    int i = 0;
    while ((this.modCount == j) && (i < k))
    {
      paramConsumer.accept(arrayOfObject[i]);
      i += 1;
    }
    if (this.modCount == j) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public E get(int paramInt)
  {
    rangeCheck(paramInt);
    return (E)elementData(paramInt);
  }
  
  public int indexOf(Object paramObject)
  {
    int i = 0;
    int j = 0;
    if (paramObject == null)
    {
      i = j;
      while (i < this.size)
      {
        if (this.elementData[i] == null) {
          return i;
        }
        i += 1;
      }
    }
    while (i < this.size)
    {
      if (paramObject.equals(this.elementData[i])) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public Iterator<E> iterator()
  {
    return new MyArrayList.Itr(null);
  }
  
  public int lastIndexOf(Object paramObject)
  {
    if (paramObject == null)
    {
      i = this.size - 1;
      while (i >= 0)
      {
        if (this.elementData[i] == null) {
          return i;
        }
        i -= 1;
      }
    }
    int i = this.size - 1;
    while (i >= 0)
    {
      if (paramObject.equals(this.elementData[i])) {
        return i;
      }
      i -= 1;
    }
    return -1;
  }
  
  public ListIterator<E> listIterator()
  {
    return new MyArrayList.ListItr(0);
  }
  
  public ListIterator<E> listIterator(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= this.size)) {
      return new MyArrayList.ListItr(paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Index: ");
    localStringBuilder.append(paramInt);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public E remove(int paramInt)
  {
    rangeCheck(paramInt);
    this.modCount += 1;
    Object localObject = elementData(paramInt);
    int i = this.size - paramInt - 1;
    if (i > 0) {
      System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
    }
    Object[] arrayOfObject = this.elementData;
    paramInt = this.size - 1;
    this.size = paramInt;
    arrayOfObject[paramInt] = null;
    return (E)localObject;
  }
  
  public boolean remove(Object paramObject)
  {
    if (paramObject == null)
    {
      i = 0;
      while (i < this.size)
      {
        if (this.elementData[i] == null)
        {
          fastRemove(i);
          return true;
        }
        i += 1;
      }
    }
    int i = 0;
    while (i < this.size)
    {
      if (paramObject.equals(this.elementData[i]))
      {
        fastRemove(i);
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    paramCollection.getClass();
    return batchRemove(paramCollection, false);
  }
  
  protected void removeRange(int paramInt1, int paramInt2)
  {
    this.modCount += 1;
    int i = this.size;
    System.arraycopy(this.elementData, paramInt2, this.elementData, paramInt1, i - paramInt2);
    paramInt2 = this.size - (paramInt2 - paramInt1);
    paramInt1 = paramInt2;
    while (paramInt1 < this.size)
    {
      this.elementData[paramInt1] = null;
      paramInt1 += 1;
    }
    this.size = paramInt2;
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    paramCollection.getClass();
    return batchRemove(paramCollection, true);
  }
  
  public E set(int paramInt, E paramE)
  {
    rangeCheck(paramInt);
    Object localObject = elementData(paramInt);
    this.elementData[paramInt] = paramE;
    return (E)localObject;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public List<E> subList(int paramInt1, int paramInt2)
  {
    subListRangeCheck(paramInt1, paramInt2, this.size);
    return new MyArrayList.SubList(this, 0, paramInt1, paramInt2);
  }
  
  public Object[] toArray()
  {
    return Arrays.copyOf(this.elementData, this.size);
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    if (paramArrayOfT.length < this.size) {
      return (Object[])Arrays.copyOf(this.elementData, this.size, paramArrayOfT.getClass());
    }
    System.arraycopy(this.elementData, 0, paramArrayOfT, 0, this.size);
    if (paramArrayOfT.length > this.size) {
      paramArrayOfT[this.size] = null;
    }
    return paramArrayOfT;
  }
  
  public void trimToSize()
  {
    this.modCount += 1;
    if (this.size < this.elementData.length)
    {
      Object[] arrayOfObject;
      if (this.size == 0) {
        arrayOfObject = EMPTY_ELEMENTDATA;
      } else {
        arrayOfObject = Arrays.copyOf(this.elementData, this.size);
      }
      this.elementData = arrayOfObject;
    }
  }
  
  private class Itr
    implements Iterator<E>
  {
    int cursor;
    int expectedModCount = MyArrayList.access$100(MyArrayList.this);
    int lastRet = -1;
    
    private Itr() {}
    
    final void checkForComodification()
    {
      if (MyArrayList.access$500(MyArrayList.this) == this.expectedModCount) {
        return;
      }
      throw new ConcurrentModificationException();
    }
    
    public void forEachRemaining(Consumer<? super E> paramConsumer)
    {
      paramConsumer.getClass();
      int j = MyArrayList.this.size;
      int i = this.cursor;
      if (i >= j) {
        return;
      }
      Object[] arrayOfObject = MyArrayList.this.elementData;
      if (i < arrayOfObject.length)
      {
        while ((i != j) && (MyArrayList.access$400(MyArrayList.this) == this.expectedModCount))
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
      return this.cursor != MyArrayList.this.size;
    }
    
    public E next()
    {
      checkForComodification();
      int i = this.cursor;
      if (i < MyArrayList.this.size)
      {
        Object[] arrayOfObject = MyArrayList.this.elementData;
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
        MyArrayList.this.remove(this.lastRet);
        this.cursor = this.lastRet;
        this.lastRet = -1;
        this.expectedModCount = MyArrayList.access$300(MyArrayList.this);
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
  
  private class ListItr
    extends MyArrayList<E>.Itr
    implements ListIterator<E>
  {
    ListItr(int paramInt)
    {
      super(null);
      this.cursor = paramInt;
    }
    
    public void add(E paramE)
    {
      checkForComodification();
      try
      {
        int i = this.cursor;
        MyArrayList.this.add(i, paramE);
        this.cursor = (i + 1);
        this.lastRet = -1;
        this.expectedModCount = MyArrayList.access$600(MyArrayList.this);
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
        Object[] arrayOfObject = MyArrayList.this.elementData;
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
        MyArrayList.this.set(this.lastRet, paramE);
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
  
  private class SubList
    extends AbstractList<E>
    implements RandomAccess
  {
    private final int offset;
    private final AbstractList<E> parent;
    private final int parentOffset;
    int size;
    
    SubList(int paramInt1, int paramInt2, int paramInt3)
    {
      this.parent = paramInt1;
      this.parentOffset = paramInt3;
      this.offset = (paramInt2 + paramInt3);
      int i;
      this.size = (i - paramInt3);
      this.modCount = MyArrayList.access$700(MyArrayList.this);
    }
    
    private void checkForComodification()
    {
      if (MyArrayList.access$1300(MyArrayList.this) == this.modCount) {
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
      return (E)MyArrayList.this.elementData(this.offset + paramInt);
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
        int expectedModCount = MyArrayList.access$800(MyArrayList.this);
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
            this.expectedModCount = MyArrayList.access$1100(MyArrayList.this);
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
          if (this.expectedModCount == MyArrayList.access$1200(MyArrayList.this)) {
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
          Object[] arrayOfObject = MyArrayList.this.elementData;
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
            Object[] arrayOfObject = MyArrayList.this.elementData;
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
            Object[] arrayOfObject = MyArrayList.this.elementData;
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
            this.expectedModCount = MyArrayList.access$1000(MyArrayList.this);
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
            MyArrayList.this.set(this.val$offset + this.lastRet, paramAnonymousE);
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
      Object localObject = MyArrayList.this.elementData(this.offset + paramInt);
      MyArrayList.this.elementData[(this.offset + paramInt)] = paramE;
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
      return new SubList(MyArrayList.this, this, this.offset, paramInt1, paramInt2);
    }
  }
}


/* Location:              C:\Users\sherl\Desktop\dex2jar-2.0\dex2jar-2.0\SampleForPaper\classes0-dex2jar.jar!\MyArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */