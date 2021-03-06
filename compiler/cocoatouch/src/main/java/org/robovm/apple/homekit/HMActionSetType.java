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
package org.robovm.apple.homekit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HMActionSetType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMActionSetType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HMActionSetType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HMActionSetType toObject(Class<HMActionSetType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMActionSetType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMActionSetType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMActionSetType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMActionSetType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HMActionSetType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMActionSetType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HMActionSetType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMActionSetType WakeUp = new HMActionSetType("WakeUp");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMActionSetType Sleep = new HMActionSetType("Sleep");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMActionSetType HomeDeparture = new HMActionSetType("HomeDeparture");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMActionSetType HomeArrival = new HMActionSetType("HomeArrival");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMActionSetType UserDefined = new HMActionSetType("UserDefined");
    /**
     * @since Available in iOS 10.0 and later.
     */
    public static final HMActionSetType TriggerOwned = new HMActionSetType("TriggerOwned");
    /*</constants>*/
    
    private static /*<name>*/HMActionSetType/*</name>*/[] values = new /*<name>*/HMActionSetType/*</name>*/[] {/*<value_list>*/WakeUp, Sleep, HomeDeparture, HomeArrival, UserDefined, TriggerOwned/*</value_list>*/};
    
    /*<name>*/HMActionSetType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HMActionSetType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HMActionSetType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMActionSetType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeWakeUp", optional=true)
        public static native NSString WakeUp();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeSleep", optional=true)
        public static native NSString Sleep();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeHomeDeparture", optional=true)
        public static native NSString HomeDeparture();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeHomeArrival", optional=true)
        public static native NSString HomeArrival();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeUserDefined", optional=true)
        public static native NSString UserDefined();
        /**
         * @since Available in iOS 10.0 and later.
         */
        @GlobalValue(symbol="HMActionSetTypeTriggerOwned", optional=true)
        public static native NSString TriggerOwned();
        /*</values>*/
    }
}
