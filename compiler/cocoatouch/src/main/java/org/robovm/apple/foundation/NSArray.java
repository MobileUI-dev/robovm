/*
 * Copyright (C) 2013-2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSArray<T extends NSObject>/*</name>*/
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration, NSPropertyList, List<T>/*</implements>*/ {

    public static class NSArrayPtr<T extends NSObject> extends Ptr<NSArray<T>, NSArrayPtr<T>> {}
    
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            return (NSArray<?>) NSObject.Marshaler.toObject(cls, handle, flags);
        }
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static long toNative(List<?> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<?> o = null;
            if (l instanceof NSArray) {
                o = (NSArray<?>) l;
            } else {
                o = new NSArray<NSObject>((List<NSObject>) l);
            }
            return NSObject.Marshaler.toNative(o, flags);
        }
    }
    
    public static class AsIntegerListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<Integer> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<Integer> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(n.intValue());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<Integer> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (Integer i : l) {
                array.add(NSNumber.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    public static class AsDoubleListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<Double> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<Double> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(n.doubleValue());
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<Double> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (Double i : l) {
                array.add(NSNumber.valueOf(i));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    public static class AsStringListMarshaler {
        @MarshalsPointer
        public static List<String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<?> o = (NSArray<?>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringList();
        }
        @MarshalsPointer
        public static long toNative(List<String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSArray.fromStrings(l), flags);
        }
    }

    static class ListAdapter<U extends NSObject> extends AbstractList<U> {
        protected final NSArray<U> array;

        ListAdapter(NSArray<U> array) {
            this.array = array;
        }

        @Override
        public U get(int index) {
            checkIndex(index);
            return (U) array.getObjectAt(index);
        }

        protected void checkIndex(int index) {
            int size = (int) array.getCount();
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
            }
        }

        @Override
        public int size() {
            return (int) array.getCount();
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof NSObject) {
                return array.containsObject((U) o);
            }
            return false;
        }
        
        @Override
        public int indexOf(Object o) {
            if (o instanceof NSObject) {
                return (int) array.indexOfObject((U) o);
            }
            return -1;
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    private AbstractList<T> adapter = createAdapter();
    
    /*<constructors>*/
    public NSArray() {}
    protected NSArray(Handle h, long handle) { super(h, handle); }
    protected NSArray(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSArray(Collection<T> c) {
        super((SkipInit) null);
        if (c == null) {
            throw new NullPointerException("c");
        }
        if (c instanceof NSArray) {
            initObject(init((NSArray<T>) c));
        } else {
            ObjCObject[] objects = c.toArray(new ObjCObject[c.size()]);
            initWithObjects(objects);
        }
    }
    
    @SuppressWarnings("unchecked")
    public NSArray(T ... objects) {
        super((SkipInit) null);
        if (objects == null) {
            throw new NullPointerException("objects");
        }
        initWithObjects(objects);
    }
    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    @Property(selector = "firstObject")
    public native T first();
    @Property(selector = "lastObject")
    public native T last();
    @Property(selector = "sortedArrayHint")
    public native NSData getSortedArrayHint();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("null values are not allowed in NSArray. Use NSNull instead.");
        }
    }
    
    private void initWithObjects(ObjCObject[] objects) {
        VoidPtr.VoidPtrPtr ptr = null;
        if (objects.length > 0) {
            ptr = Struct.allocate(VoidPtr.VoidPtrPtr.class, objects.length);
            for (int i = 0; i < objects.length; i++) {
                checkNull(objects[i]);
                ptr.set(objects[i].getHandle());
                ptr = ptr.next();
            }
            ptr = ptr.previous(objects.length);
        }
        initObject(init(ptr != null ? ptr.getHandle() : 0, objects.length));
    }
    
    protected AbstractList<T> createAdapter() {
        return new ListAdapter<T>(this);
    }
    
    @Override
    protected void afterMarshaled(int flags) {
        if (adapter == null) {
            adapter = createAdapter();
        }
        super.afterMarshaled(flags);
    }
    
    public void add(int index, T element) {
        adapter.add(index, element);
    }
    public boolean add(T e) {
        return adapter.add(e);
    }
    public boolean addAll(Collection<? extends T> c) {
        return adapter.addAll(c);
    }
    public boolean addAll(int index, Collection<? extends T> c) {
        return adapter.addAll(index, c);
    }
    public void clear() {
        adapter.clear();
    }
    public boolean contains(Object o) {
        return adapter.contains(o);
    }
    public boolean containsAll(Collection<?> c) {
        return adapter.containsAll(c);
    }
    public T get(int index) {
        return adapter.get(index);
    }
    public int indexOf(Object o) {
        return adapter.indexOf(o);
    }
    public boolean isEmpty() {
        return adapter.isEmpty();
    }
    public Iterator<T> iterator() {
        return adapter.iterator();
    }
    public int lastIndexOf(Object o) {
        return adapter.lastIndexOf(o);
    }
    public ListIterator<T> listIterator() {
        return adapter.listIterator();
    }
    public ListIterator<T> listIterator(int index) {
        return adapter.listIterator(index);
    }
    public T remove(int index) {
        return adapter.remove(index);
    }
    public boolean remove(Object o) {
        return adapter.remove(o);
    }
    public boolean removeAll(Collection<?> c) {
        return adapter.removeAll(c);
    }
    public boolean retainAll(Collection<?> c) {
        return adapter.retainAll(c);
    }
    public T set(int index, T element) {
        return adapter.set(index, element);
    }
    public int size() {
        return adapter.size();
    }
    public NSArray<T> subList(int start, int end) {
        if (start >= 0 && end <= size()) {
            if (start <= end) {
                return (NSArray<T>) getSubarray(new NSRange(start, end - start));
            }
            throw new IllegalArgumentException();
        }
        throw new IndexOutOfBoundsException();
    }
    public Object[] toArray() {
        return adapter.toArray();
    }
    public <U> U[] toArray(U[] a) {
        return adapter.toArray(a);
    }
    
    public boolean getBoolean(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).booleanValue();
        } else {
            wrongType(index, val.getClass());
            return false;
        }
    }

    public byte getByte(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).byteValue();
        } else {
            wrongType(index, val.getClass());
            return (byte)0;
        }
    }

    public short getByte(short index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).shortValue();
        } else {
            wrongType(index, val.getClass());
            return (short)0;
        }
    }

    public int getInt(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).intValue();
        } else {
            wrongType(index, val.getClass());
            return 0;
        }
    }

    public long getLong(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).longValue();
        } else {
            wrongType(index, val.getClass());
            return 0L;
        }
    }

    public float getFloat(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).floatValue();
        } else {
            wrongType(index, val.getClass());
            return 0.0F;
        }
    }

    public double getDouble(int index) {
        NSObject val = get(index);
        if(val instanceof NSNumber) {
            return ((NSNumber)val).doubleValue();
        } else {
            wrongType(index, val.getClass());
            return 0.0D;
        }
    }

    public String getString(int index) {
        NSObject val = get(index);
        if(val instanceof NSString) {
            return val.toString();
        } else {
            wrongType(index, val.getClass());
            return null;
        }
    }

    private void wrongType(int index, Class<?> type) {
        throw new ClassCastException("Wrong type at index " + index + ": " + type);
    }

    public boolean add(boolean value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public void add(int index, boolean value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public boolean add(Number value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public void add(int index, Number value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public boolean add(String value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public void add(int index, String value) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public Object set(int index, boolean element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public Object set(int index, Number element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public Object set(int index, String element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public int indexOf(boolean element) {
        return indexOf((Object)NSNumber.valueOf(element));
    }

    public int indexOf(Number element) {
        return indexOf((Object)NSNumber.valueOf(element));
    }

    public int indexOf(String element) {
        return indexOf((Object)(new NSString(element)));
    }

    public boolean remove(boolean element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public boolean remove(Number element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }

    public boolean remove(String element) {
        throw new UnsupportedOperationException("NSArray is immutable");
    }
    
    public static NSArray<?> read(java.io.File file) {
        return readFile(file.getAbsolutePath());
    }

    public void write(java.io.File file, boolean atomically) {
        writeFile(file.getAbsolutePath(), atomically);
    }
    
    /**
     * Use this method to convert a NSArray of NSString items to a List of String items. 
     * Elements of this NSArray must be of type NSString, otherwise an exception will be thrown.
     * @return
     * @throws UnsupportedOperationException when the array items are not of type NSString.
     */
    public List<String> asStringList() {
        List<String> list = new ArrayList<>();
        if (size() == 0) 
            return list;
        if (!(get(0) instanceof NSString)) 
            throw new UnsupportedOperationException("items must be of type NSString");
        
        for (T str : this) {
            list.add(str.toString());
        }
        return list;
    }
    
    public static NSArray<NSString> fromStrings (String... strings) {
        int length = strings.length;
        NSString[] nsStrings = new NSString[length];

        for (int i = 0; i < length; i++) {
            nsStrings[i] = new NSString(strings[i]);
        }
        return new NSArray<NSString>(nsStrings);
    }

    public static NSArray<NSString> fromStrings (Collection<String> strings) {
        NSString[] nsStrings = new NSString[strings.size()];

        int i = 0;
        for (String s : strings) {
            nsStrings[i] = new NSString(s);
            i++;
        }
        return new NSArray<NSString>(nsStrings);
    }
    
    public static NSArray<NSNumber> fromNumbers (Number...numbers) {
        int length = numbers.length;
        NSNumber[] nsNumbers = new NSNumber[length];
        
        for (int i = 0; i < length; i++) {
            nsNumbers[i] = NSNumber.valueOf(numbers[i]);
        }
        return new NSArray<NSNumber>(nsNumbers);
    }
    public static NSArray<NSNumber> fromNumbers (Collection<Number> numbers) {
        NSNumber[] nsNumbers = new NSNumber[numbers.size()];
        
        int i = 0;
        for (Number n : numbers) {
            nsNumbers[i] = NSNumber.valueOf(n);
            i++;
        }
        return new NSArray<NSNumber>(nsNumbers);
    }

    /*<methods>*/
    @Method(selector = "objectAtIndex:")
    protected native T getObjectAt(@MachineSizedUInt long index);
    @Method(selector = "initWithObjects:count:")
    protected native @Pointer long init(@Pointer long objects, @MachineSizedUInt long cnt);
    @Method(selector = "containsObject:")
    protected native boolean containsObject(T anObject);
    @Method(selector = "indexOfObject:")
    protected native @MachineSizedUInt long indexOfObject(T anObject);
    @Method(selector = "subarrayWithRange:")
    protected native NSArray<T> getSubarray(@ByVal NSRange range);
    @Method(selector = "initWithArray:")
    protected native @Pointer long init(NSArray<T> array);
    /**
     * @deprecated Use writeToURL:error:
     */
    @Deprecated
    @Method(selector = "writeToFile:atomically:")
    protected native boolean writeFile(String path, boolean atomically);
    /**
     * @deprecated Use writeToURL:error:
     */
    @Deprecated
    @Method(selector = "writeToURL:atomically:")
    public native boolean write(NSURL url, boolean atomically);
    /**
     * @deprecated Use arrayWithContentsOfURL:error:
     */
    @Deprecated
    @Method(selector = "arrayWithContentsOfFile:")
    protected static native NSArray<?> readFile(String path);
    /**
     * @deprecated Use arrayWithContentsOfURL:error:
     */
    @Deprecated
    @Method(selector = "arrayWithContentsOfURL:")
    public static native NSArray<?> read(NSURL url);
    @Method(selector = "addObserver:toObjectsAtIndexes:forKeyPath:options:context:")
    public native void addObserver(NSObject observer, NSIndexSet indexes, String keyPath, NSKeyValueObservingOptions options, VoidPtr context);
    @Method(selector = "removeObserver:fromObjectsAtIndexes:forKeyPath:context:")
    public native void removeObserver(NSObject observer, NSIndexSet indexes, String keyPath, VoidPtr context);
    /*</methods>*/
}
