
/*
Copyright (c) 2003-2006,  Pete Sanderson and Kenneth Vollmar

Developed by Pete Sanderson (psanderson@otterbein.edu)
and Kenneth Vollmar (kenvollmar@missouristate.edu)

Permission is hereby granted, free of charge, to any person obtaining 
a copy of this software and associated documentation files (the 
"Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, 
distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject 
to the following conditions:

The above copyright notice and this permission notice shall be 
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

(MIT license, http://www.opensource.org/licenses/mit-license.html)
 */

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Portal to Mars
 *
 * @author Pete Sanderson
 * @version March 2006
 **/

public class Mars {
    private static Class[] classes = {FlatArcDarkIJTheme.class,
            FlatArcDarkIJTheme.class, FlatArcOrangeIJTheme.class,
            FlatArcIJTheme.class, FlatCarbonIJTheme.class, FlatCobalt2IJTheme.class,
            FlatCyanLightIJTheme.class, FlatDarkFlatIJTheme.class, FlatDarkPurpleIJTheme.class,
            FlatDraculaIJTheme.class, FlatGradiantoDarkFuchsiaIJTheme.class,
            FlatGradiantoDeepOceanIJTheme.class, FlatGradiantoMidnightBlueIJTheme.class,
            FlatGradiantoNatureGreenIJTheme.class, FlatGrayIJTheme.class,
            FlatGruvboxDarkHardIJTheme.class, FlatGruvboxDarkMediumIJTheme.class,
            FlatGruvboxDarkSoftIJTheme.class, FlatHiberbeeDarkIJTheme.class,
            FlatHighContrastIJTheme.class, FlatLightFlatIJTheme.class,
            FlatMaterialDesignDarkIJTheme.class, FlatMonocaiIJTheme.class,
            FlatNordIJTheme.class, FlatOneDarkIJTheme.class,
            FlatSolarizedDarkIJTheme.class, FlatSolarizedLightIJTheme.class,
            FlatSpacegrayIJTheme.class, FlatVuesionIJTheme.class};

    public static void main(String[] args) {
        final var random = new Random();
        final var i = random.nextInt(classes.length);
        try {
            final var setup = classes[i].getMethod("setup");
//            final var setup=FlatCyanLightIJTheme.class.getMethod("setup");
            setup.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            FlatOneDarkIJTheme.setup();
        }
        new mars.MarsLaunch(args);
    }
}

