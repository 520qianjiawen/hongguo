package p013n;

import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final byte[] f1125A;
    public static final String[] B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f1126C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final byte[] f1127D;
    public static final d E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final d[][] f1128F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final d[] f1129G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final HashMap[] f1130H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final HashMap[] f1131I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final Set f1132J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final HashMap f1133K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final Charset f1134L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final byte[] f1135M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final byte[] f1136N;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final boolean f1137l = Log.isLoggable("ExifInterface", 3);
    public static final int[] m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f1138n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final byte[] f1139o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final byte[] f1140p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final byte[] f1141q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final byte[] f1142r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final byte[] f1143s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final byte[] f1144t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final byte[] f1145u;
    public static final byte[] v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final byte[] f1146w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final byte[] f1147x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final byte[] f1148y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final byte[] f1149z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FileDescriptor f1150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1151b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap[] f1152c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet f1153d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ByteOrder f1154e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1155f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1156g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1157h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1158i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1159j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public c f1160k;

    static {
        Arrays.asList(1, 6, 3, 8);
        Arrays.asList(2, 7, 4, 5);
        m = new int[]{8, 8, 8};
        f1138n = new int[]{8};
        f1139o = new byte[]{-1, -40, -1};
        f1140p = new byte[]{102, 116, 121, 112};
        f1141q = new byte[]{109, 105, 102, 49};
        f1142r = new byte[]{104, 101, 105, 99};
        f1143s = new byte[]{97, 118, 105, 102};
        f1144t = new byte[]{97, 118, 105, 115};
        f1145u = new byte[]{79, 76, 89, 77, 80, 0};
        v = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        f1146w = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
        f1147x = "XML:com.adobe.xmp\u0000\u0000\u0000\u0000\u0000".getBytes(StandardCharsets.UTF_8);
        f1148y = new byte[]{82, 73, 70, 70};
        f1149z = new byte[]{87, 69, 66, 80};
        f1125A = new byte[]{69, 88, 73, 70};
        "VP8X".getBytes(Charset.defaultCharset());
        "VP8L".getBytes(Charset.defaultCharset());
        "VP8 ".getBytes(Charset.defaultCharset());
        "ANIM".getBytes(Charset.defaultCharset());
        "ANMF".getBytes(Charset.defaultCharset());
        B = new String[]{"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        f1126C = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        f1127D = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        d[] dVarArr = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d(256, 3, 4, "ImageWidth"), new d(257, 3, 4, "ImageLength"), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d(273, 3, 4, "StripOffsets"), new d("Orientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d(278, 3, 4, "RowsPerStrip"), new d(279, 3, 4, "StripByteCounts"), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("SensorTopBorder", 4, 4), new d("SensorLeftBorder", 5, 4), new d("SensorBottomBorder", 6, 4), new d("SensorRightBorder", 7, 4), new d("ISO", 23, 3), new d("JpgFromRaw", 46, 7), new d("Xmp", 700, 1)};
        d[] dVarArr2 = {new d("ExposureTime", 33434, 5), new d("FNumber", 33437, 5), new d("ExposureProgram", 34850, 3), new d("SpectralSensitivity", 34852, 2), new d("PhotographicSensitivity", 34855, 3), new d("OECF", 34856, 7), new d("SensitivityType", 34864, 3), new d("StandardOutputSensitivity", 34865, 4), new d("RecommendedExposureIndex", 34866, 4), new d("ISOSpeed", 34867, 4), new d("ISOSpeedLatitudeyyy", 34868, 4), new d("ISOSpeedLatitudezzz", 34869, 4), new d("ExifVersion", 36864, 2), new d("DateTimeOriginal", 36867, 2), new d("DateTimeDigitized", 36868, 2), new d("OffsetTime", 36880, 2), new d("OffsetTimeOriginal", 36881, 2), new d("OffsetTimeDigitized", 36882, 2), new d("ComponentsConfiguration", 37121, 7), new d("CompressedBitsPerPixel", 37122, 5), new d("ShutterSpeedValue", 37377, 10), new d("ApertureValue", 37378, 5), new d("BrightnessValue", 37379, 10), new d("ExposureBiasValue", 37380, 10), new d("MaxApertureValue", 37381, 5), new d("SubjectDistance", 37382, 5), new d("MeteringMode", 37383, 3), new d("LightSource", 37384, 3), new d("Flash", 37385, 3), new d("FocalLength", 37386, 5), new d("SubjectArea", 37396, 3), new d("MakerNote", 37500, 7), new d("UserComment", 37510, 7), new d("SubSecTime", 37520, 2), new d("SubSecTimeOriginal", 37521, 2), new d("SubSecTimeDigitized", 37522, 2), new d("FlashpixVersion", 40960, 7), new d("ColorSpace", 40961, 3), new d(40962, 3, 4, "PixelXDimension"), new d(40963, 3, 4, "PixelYDimension"), new d("RelatedSoundFile", 40964, 2), new d("InteroperabilityIFDPointer", 40965, 4), new d("FlashEnergy", 41483, 5), new d("SpatialFrequencyResponse", 41484, 7), new d("FocalPlaneXResolution", 41486, 5), new d("FocalPlaneYResolution", 41487, 5), new d("FocalPlaneResolutionUnit", 41488, 3), new d("SubjectLocation", 41492, 3), new d("ExposureIndex", 41493, 5), new d("SensingMethod", 41495, 3), new d("FileSource", 41728, 7), new d("SceneType", 41729, 7), new d("CFAPattern", 41730, 7), new d("CustomRendered", 41985, 3), new d("ExposureMode", 41986, 3), new d("WhiteBalance", 41987, 3), new d("DigitalZoomRatio", 41988, 5), new d("FocalLengthIn35mmFilm", 41989, 3), new d("SceneCaptureType", 41990, 3), new d("GainControl", 41991, 3), new d("Contrast", 41992, 3), new d("Saturation", 41993, 3), new d("Sharpness", 41994, 3), new d("DeviceSettingDescription", 41995, 7), new d("SubjectDistanceRange", 41996, 3), new d("ImageUniqueID", 42016, 2), new d("CameraOwnerName", 42032, 2), new d("BodySerialNumber", 42033, 2), new d("LensSpecification", 42034, 5), new d("LensMake", 42035, 2), new d("LensModel", 42036, 2), new d("Gamma", 42240, 5), new d("DNGVersion", 50706, 1), new d(50720, 3, 4, "DefaultCropSize")};
        d[] dVarArr3 = {new d("GPSVersionID", 0, 1), new d("GPSLatitudeRef", 1, 2), new d(2, 5, 10, "GPSLatitude"), new d("GPSLongitudeRef", 3, 2), new d(4, 5, 10, "GPSLongitude"), new d("GPSAltitudeRef", 5, 1), new d("GPSAltitude", 6, 5), new d("GPSTimeStamp", 7, 5), new d("GPSSatellites", 8, 2), new d("GPSStatus", 9, 2), new d("GPSMeasureMode", 10, 2), new d("GPSDOP", 11, 5), new d("GPSSpeedRef", 12, 2), new d("GPSSpeed", 13, 5), new d("GPSTrackRef", 14, 2), new d("GPSTrack", 15, 5), new d("GPSImgDirectionRef", 16, 2), new d("GPSImgDirection", 17, 5), new d("GPSMapDatum", 18, 2), new d("GPSDestLatitudeRef", 19, 2), new d("GPSDestLatitude", 20, 5), new d("GPSDestLongitudeRef", 21, 2), new d("GPSDestLongitude", 22, 5), new d("GPSDestBearingRef", 23, 2), new d("GPSDestBearing", 24, 5), new d("GPSDestDistanceRef", 25, 2), new d("GPSDestDistance", 26, 5), new d("GPSProcessingMethod", 27, 7), new d("GPSAreaInformation", 28, 7), new d("GPSDateStamp", 29, 2), new d("GPSDifferential", 30, 3), new d("GPSHPositioningError", 31, 5)};
        d[] dVarArr4 = {new d("InteroperabilityIndex", 1, 2)};
        d[] dVarArr5 = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d(256, 3, 4, "ThumbnailImageWidth"), new d(257, 3, 4, "ThumbnailImageLength"), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d(273, 3, 4, "StripOffsets"), new d("ThumbnailOrientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d(278, 3, 4, "RowsPerStrip"), new d(279, 3, 4, "StripByteCounts"), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("DNGVersion", 50706, 1), new d(50720, 3, 4, "DefaultCropSize")};
        E = new d("StripOffsets", 273, 3);
        f1128F = new d[][]{dVarArr, dVarArr2, dVarArr3, dVarArr4, dVarArr5, dVarArr, new d[]{new d("ThumbnailImage", 256, 7), new d("CameraSettingsIFDPointer", 8224, 4), new d("ImageProcessingIFDPointer", 8256, 4)}, new d[]{new d("PreviewImageStart", 257, 4), new d("PreviewImageLength", 258, 4)}, new d[]{new d("AspectFrame", 4371, 3)}, new d[]{new d("ColorSpace", 55, 3)}};
        f1129G = new d[]{new d("SubIFDPointer", 330, 4), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("InteroperabilityIFDPointer", 40965, 4), new d("CameraSettingsIFDPointer", 8224, 1), new d("ImageProcessingIFDPointer", 8256, 1)};
        f1130H = new HashMap[10];
        f1131I = new HashMap[10];
        f1132J = Collections.unmodifiableSet(new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance")));
        f1133K = new HashMap();
        Charset charsetForName = Charset.forName("US-ASCII");
        f1134L = charsetForName;
        f1135M = "Exif\u0000\u0000".getBytes(charsetForName);
        f1136N = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(charsetForName);
        Locale locale = Locale.US;
        new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        int i2 = 0;
        while (true) {
            d[][] dVarArr6 = f1128F;
            if (i2 >= dVarArr6.length) {
                HashMap map = f1133K;
                d[] dVarArr7 = f1129G;
                map.put(Integer.valueOf(dVarArr7[0].f1119a), 5);
                map.put(Integer.valueOf(dVarArr7[1].f1119a), 1);
                map.put(Integer.valueOf(dVarArr7[2].f1119a), 2);
                map.put(Integer.valueOf(dVarArr7[3].f1119a), 3);
                map.put(Integer.valueOf(dVarArr7[4].f1119a), 7);
                map.put(Integer.valueOf(dVarArr7[5].f1119a), 8);
                Pattern.compile(".*[1-9].*");
                Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            f1130H[i2] = new HashMap();
            f1131I[i2] = new HashMap();
            for (d dVar : dVarArr6[i2]) {
                f1130H[i2].put(Integer.valueOf(dVar.f1119a), dVar);
                f1131I[i2].put(dVar.f1120b, dVar);
            }
            i2++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00ad A[Catch: all -> 0x002e, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x002e, blocks: (B:3:0x001f, B:5:0x0022, B:12:0x0037, B:18:0x0054, B:25:0x0067, B:31:0x007a, B:28:0x006f, B:29:0x0073, B:30:0x0077, B:32:0x0084, B:34:0x008d, B:36:0x0093, B:38:0x0099, B:40:0x009f, B:45:0x00ad), top: B:55:0x001f }] */
    /* JADX WARN: Code duplicated, block: B:58:? A[RETURN, SYNTHETIC] */
    public g(ByteArrayInputStream byteArrayInputStream) {
        d[][] dVarArr = f1128F;
        this.f1152c = new HashMap[dVarArr.length];
        this.f1153d = new HashSet(dVarArr.length);
        this.f1154e = ByteOrder.BIG_ENDIAN;
        boolean z2 = f1137l;
        this.f1150a = null;
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            try {
                try {
                    this.f1152c[i2] = new HashMap();
                } catch (Throwable th) {
                    a();
                    if (z2) {
                        p();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                if (z2) {
                    Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file (ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);
                }
                a();
                if (!z2) {
                    return;
                }
            } catch (UnsupportedOperationException e3) {
                e = e3;
                if (z2) {
                    Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file (ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);
                }
                a();
                if (!z2) {
                    return;
                }
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream, 5000);
        int iF = f(bufferedInputStream);
        this.f1151b = iF;
        if (iF == 4 || iF == 9 || iF == 13 || iF == 14) {
            b bVar = new b(bufferedInputStream);
            int i3 = this.f1151b;
            if (i3 == 4) {
                e(bVar, 0, 0);
            } else if (i3 == 13) {
                h(bVar);
            } else if (i3 == 9) {
                i(bVar);
            } else if (i3 == 14) {
                l(bVar);
            }
        } else {
            f fVar = new f(bufferedInputStream);
            int i4 = this.f1151b;
            if (i4 == 12 || i4 == 15) {
                d(fVar, i4);
            } else if (i4 == 7) {
                g(fVar);
            } else if (i4 == 10) {
                k(fVar);
            } else {
                j(fVar);
            }
            fVar.b(this.f1156g);
            u(fVar);
        }
        a();
        if (!z2) {
            return;
        }
        p();
    }

    public static ByteOrder q(b bVar) throws IOException {
        short s2 = bVar.readShort();
        boolean z2 = f1137l;
        if (s2 == 18761) {
            if (z2) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s2 == 19789) {
            if (z2) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s2));
    }

    public final void a() {
        String strB = b("DateTimeOriginal");
        HashMap[] mapArr = this.f1152c;
        if (strB != null && b("DateTime") == null) {
            HashMap map = mapArr[0];
            byte[] bytes = strB.concat("\u0000").getBytes(f1134L);
            map.put("DateTime", new c(bytes, 2, bytes.length));
        }
        if (b("ImageWidth") == null) {
            mapArr[0].put("ImageWidth", c.a(0L, this.f1154e));
        }
        if (b("ImageLength") == null) {
            mapArr[0].put("ImageLength", c.a(0L, this.f1154e));
        }
        if (b("Orientation") == null) {
            mapArr[0].put("Orientation", c.a(0L, this.f1154e));
        }
        if (b("LightSource") == null) {
            mapArr[1].put("LightSource", c.a(0L, this.f1154e));
        }
    }

    public final String b(String str) {
        c cVarC = c(str);
        if (cVarC != null) {
            int i2 = cVarC.f1115a;
            if (str.equals("GPSTimeStamp")) {
                if (i2 != 5 && i2 != 10) {
                    Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + i2);
                    return null;
                }
                e[] eVarArr = (e[]) cVarC.g(this.f1154e);
                if (eVarArr == null || eVarArr.length != 3) {
                    Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(eVarArr));
                    return null;
                }
                e eVar = eVarArr[0];
                Integer numValueOf = Integer.valueOf((int) (eVar.f1123a / eVar.f1124b));
                e eVar2 = eVarArr[1];
                Integer numValueOf2 = Integer.valueOf((int) (eVar2.f1123a / eVar2.f1124b));
                e eVar3 = eVarArr[2];
                return String.format("%02d:%02d:%02d", numValueOf, numValueOf2, Integer.valueOf((int) (eVar3.f1123a / eVar3.f1124b)));
            }
            if (!f1132J.contains(str)) {
                return cVarC.f(this.f1154e);
            }
            try {
                return Double.toString(cVarC.d(this.f1154e));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public final c c(String str) {
        c cVar;
        int i2;
        c cVar2;
        if ("ISOSpeedRatings".equals(str)) {
            if (f1137l) {
                Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str = "PhotographicSensitivity";
        }
        if ("Xmp".equals(str) && (i2 = this.f1151b) != 4 && ((i2 == 9 || i2 == 15 || i2 == 12 || i2 == 13) && (cVar2 = this.f1160k) != null)) {
            return cVar2;
        }
        for (int i3 = 0; i3 < f1128F.length; i3++) {
            c cVar3 = (c) this.f1152c[i3].get(str);
            if (cVar3 != null) {
                return cVar3;
            }
        }
        if (!"Xmp".equals(str) || (cVar = this.f1160k) == null) {
            return null;
        }
        return cVar;
    }

    public final void d(f fVar, int i2) {
        String strExtractMetadata;
        String strExtractMetadata2;
        String strExtractMetadata3;
        int i3;
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 28) {
            throw new UnsupportedOperationException("Reading EXIF from HEIC files is supported from SDK 28 and above");
        }
        if (i2 == 15 && i4 < 31) {
            throw new UnsupportedOperationException("Reading EXIF from AVIF files is supported from SDK 31 and above");
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(new a(fVar));
                String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(33);
                String strExtractMetadata5 = mediaMetadataRetriever.extractMetadata(34);
                String strExtractMetadata6 = mediaMetadataRetriever.extractMetadata(26);
                String strExtractMetadata7 = mediaMetadataRetriever.extractMetadata(17);
                if ("yes".equals(strExtractMetadata6)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(29);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(30);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(31);
                } else if ("yes".equals(strExtractMetadata7)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(19);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(24);
                } else {
                    strExtractMetadata = null;
                    strExtractMetadata2 = null;
                    strExtractMetadata3 = null;
                }
                HashMap[] mapArr = this.f1152c;
                if (strExtractMetadata != null) {
                    mapArr[0].put("ImageWidth", c.c(Integer.parseInt(strExtractMetadata), this.f1154e));
                }
                if (strExtractMetadata3 != null) {
                    mapArr[0].put("ImageLength", c.c(Integer.parseInt(strExtractMetadata3), this.f1154e));
                }
                if (strExtractMetadata2 != null) {
                    int i5 = Integer.parseInt(strExtractMetadata2);
                    if (i5 == 90) {
                        i3 = 6;
                    } else if (i5 != 180) {
                        i3 = i5 != 270 ? 1 : 8;
                    } else {
                        i3 = 3;
                    }
                    mapArr[0].put("Orientation", c.c(i3, this.f1154e));
                }
                if (strExtractMetadata4 != null && strExtractMetadata5 != null) {
                    int i6 = Integer.parseInt(strExtractMetadata4);
                    int i7 = Integer.parseInt(strExtractMetadata5);
                    if (i7 <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    fVar.b(i6);
                    byte[] bArr = new byte[6];
                    fVar.readFully(bArr);
                    int i8 = i6 + 6;
                    int i9 = i7 - 6;
                    if (!Arrays.equals(bArr, f1135M)) {
                        throw new IOException("Invalid identifier");
                    }
                    byte[] bArr2 = new byte[i9];
                    fVar.readFully(bArr2);
                    this.f1156g = i8;
                    r(bArr2, 0);
                }
                String strExtractMetadata8 = mediaMetadataRetriever.extractMetadata(41);
                String strExtractMetadata9 = mediaMetadataRetriever.extractMetadata(42);
                if (strExtractMetadata8 != null && strExtractMetadata9 != null) {
                    int i10 = Integer.parseInt(strExtractMetadata8);
                    int i11 = Integer.parseInt(strExtractMetadata9);
                    long j2 = i10;
                    fVar.b(j2);
                    byte[] bArr3 = new byte[i11];
                    fVar.readFully(bArr3);
                    this.f1160k = new c(j2, bArr3, 1, i11);
                }
                if (f1137l) {
                    Log.d("ExifInterface", "Heif meta: " + strExtractMetadata + "x" + strExtractMetadata3 + ", rotation " + strExtractMetadata2);
                }
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException unused) {
                }
            } catch (RuntimeException e2) {
                throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.", e2);
            }
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
                throw th;
            } catch (IOException unused2) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00ac A[FALL_THROUGH] */
    /* JADX WARN: Code duplicated, block: B:36:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:55:0x0158 A[LOOP:0: B:10:0x0034->B:55:0x0158, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:72:0x015f A[SYNTHETIC] */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x009e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:30:0x00a1. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x00a4. Please report as an issue. */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public final void e(p013n.b r23, int r24, int r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 484
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p013n.g.e(n.b, int, int):void");
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0142  */
    /* JADX WARN: Code duplicated, block: B:104:0x0144  */
    /* JADX WARN: Code duplicated, block: B:119:0x0163 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:121:0x0166  */
    /* JADX WARN: Code duplicated, block: B:124:0x016d  */
    /* JADX WARN: Code duplicated, block: B:127:0x0176 A[LOOP:2: B:122:0x0168->B:127:0x0176, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:130:0x017c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:132:0x017f  */
    /* JADX WARN: Code duplicated, block: B:135:0x0186  */
    /* JADX WARN: Code duplicated, block: B:138:0x018f A[LOOP:3: B:133:0x0181->B:138:0x018f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:142:0x0199  */
    /* JADX WARN: Code duplicated, block: B:145:0x01a3 A[LOOP:4: B:140:0x0194->B:145:0x01a3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:147:0x01a8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:149:0x01ab A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:168:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:16:0x004a  */
    /* JADX WARN: Code duplicated, block: B:174:0x00f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x0179 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x0173 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x0192 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x018c A[EDGE_INSN: B:183:0x018c->B:137:0x018c BREAK  A[LOOP:3: B:133:0x0181->B:138:0x018f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x01a6 A[EDGE_INSN: B:184:0x01a6->B:146:0x01a6 BREAK  A[LOOP:4: B:140:0x0194->B:145:0x01a3], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x018c A[EDGE_INSN: B:185:0x018c->B:137:0x018c BREAK  A[LOOP:3: B:133:0x0181->B:138:0x018f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00f1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:83:0x010f  */
    /* JADX WARN: Code duplicated, block: B:97:0x012b A[RETURN] */
    public final int f(BufferedInputStream bufferedInputStream) throws Throwable {
        b bVar;
        int i2;
        b bVar2;
        int i3;
        b bVar3;
        b bVar4;
        int i4;
        b bVar5;
        b bVar6;
        int i5;
        int i6;
        byte[] bArr;
        int i7;
        int i8;
        byte[] bArr2;
        int i9;
        byte[] bArr3;
        b bVar7;
        short s2;
        long j2;
        bufferedInputStream.mark(5000);
        byte[] bArr4 = new byte[5000];
        bufferedInputStream.read(bArr4);
        bufferedInputStream.reset();
        int i10 = 0;
        while (true) {
            byte[] bArr5 = f1139o;
            if (i10 >= bArr5.length) {
                return 4;
            }
            if (bArr4[i10] != bArr5[i10]) {
                byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
                for (int i11 = 0; i11 < bytes.length; i11++) {
                    if (bArr4[i11] != bytes[i11]) {
                        int i12 = 1;
                        try {
                            bVar2 = new b(bArr4);
                            try {
                                try {
                                    long j3 = bVar2.readInt();
                                    byte[] bArr6 = new byte[4];
                                    bVar2.readFully(bArr6);
                                    if (Arrays.equals(bArr6, f1140p)) {
                                        if (j3 == 1) {
                                            j3 = bVar2.readLong();
                                            j2 = 16;
                                            if (j3 < 16) {
                                                bVar2.close();
                                                i2 = 0;
                                                i3 = 0;
                                            }
                                        } else {
                                            j2 = 8;
                                        }
                                        i2 = 0;
                                        long j4 = 5000;
                                        if (j3 > j4) {
                                            j3 = j4;
                                        }
                                        long j5 = j3 - j2;
                                        if (j5 >= 8) {
                                            try {
                                                byte[] bArr7 = new byte[4];
                                                long j6 = 0;
                                                boolean z2 = false;
                                                boolean z3 = false;
                                                boolean z4 = false;
                                                while (true) {
                                                    if (j6 < j5 / 4) {
                                                        try {
                                                            bVar2.readFully(bArr7);
                                                            if (j6 != 1) {
                                                                if (Arrays.equals(bArr7, f1141q)) {
                                                                    z2 = true;
                                                                } else if (Arrays.equals(bArr7, f1142r)) {
                                                                    z3 = true;
                                                                } else if (Arrays.equals(bArr7, f1143s) || Arrays.equals(bArr7, f1144t)) {
                                                                    z4 = true;
                                                                }
                                                                if (!z2) {
                                                                    continue;
                                                                } else if (z3) {
                                                                    bVar2.close();
                                                                    i3 = 12;
                                                                } else if (z4) {
                                                                    bVar2.close();
                                                                    i3 = 15;
                                                                }
                                                            }
                                                            j6++;
                                                        } catch (EOFException unused) {
                                                            bVar2.close();
                                                            i3 = i2;
                                                        }
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                e = e2;
                                                if (f1137l) {
                                                    Log.d("ExifInterface", "Exception parsing HEIF file type box.", e);
                                                }
                                                if (bVar2 != null) {
                                                }
                                                i3 = i2;
                                                if (i3 != 0) {
                                                    return i3;
                                                }
                                                try {
                                                    bVar4 = new b(bArr4);
                                                    try {
                                                        ByteOrder byteOrderQ = q(bVar4);
                                                        this.f1154e = byteOrderQ;
                                                        bVar4.f1112f = byteOrderQ;
                                                        s2 = bVar4.readShort();
                                                        if (s2 != 20306) {
                                                            i4 = 1;
                                                        } else {
                                                            i4 = 1;
                                                        }
                                                        bVar4.close();
                                                    } catch (Exception unused2) {
                                                        if (bVar4 != null) {
                                                            bVar4.close();
                                                        }
                                                        i4 = i2;
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        bVar3 = bVar4;
                                                        if (bVar3 != null) {
                                                            bVar3.close();
                                                        }
                                                        throw th;
                                                    }
                                                } catch (Exception unused3) {
                                                    bVar4 = null;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    bVar3 = null;
                                                }
                                                if (i4 != 0) {
                                                    return 7;
                                                }
                                                try {
                                                    bVar7 = new b(bArr4);
                                                    try {
                                                        ByteOrder byteOrderQ2 = q(bVar7);
                                                        this.f1154e = byteOrderQ2;
                                                        bVar7.f1112f = byteOrderQ2;
                                                        if (bVar7.readShort() == 85) {
                                                            i5 = 1;
                                                        } else {
                                                            i5 = i2;
                                                        }
                                                        bVar7.close();
                                                    } catch (Exception unused4) {
                                                        bVar6 = bVar7;
                                                        if (bVar6 != null) {
                                                            bVar6.close();
                                                        }
                                                        i5 = i2;
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        bVar5 = bVar7;
                                                        if (bVar5 != null) {
                                                            bVar5.close();
                                                        }
                                                        throw th;
                                                    }
                                                } catch (Exception unused5) {
                                                    bVar6 = null;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    bVar5 = null;
                                                }
                                                if (i5 != 0) {
                                                    return 10;
                                                }
                                                i6 = i2;
                                                while (true) {
                                                    bArr = f1146w;
                                                    if (i6 < bArr.length) {
                                                        i7 = 1;
                                                        break;
                                                    }
                                                    if (bArr4[i6] != bArr[i6]) {
                                                        i7 = i2;
                                                        break;
                                                    }
                                                    i6++;
                                                }
                                                if (i7 != 0) {
                                                    return 13;
                                                }
                                                i8 = i2;
                                                while (true) {
                                                    bArr2 = f1148y;
                                                    if (i8 < bArr2.length) {
                                                        i9 = i2;
                                                        while (true) {
                                                            bArr3 = f1149z;
                                                            if (i9 >= bArr3.length) {
                                                                break;
                                                            }
                                                            if (bArr4[bArr2.length + i9 + 4] != bArr3[i9]) {
                                                                break;
                                                            }
                                                            i9++;
                                                        }
                                                        if (i12 != 0) {
                                                            return 14;
                                                        }
                                                        return i2;
                                                    }
                                                    if (bArr4[i8] != bArr2[i8]) {
                                                        break;
                                                    }
                                                    i8++;
                                                }
                                                i12 = i2;
                                                if (i12 != 0) {
                                                    return 14;
                                                }
                                                return i2;
                                            }
                                        }
                                        bVar2.close();
                                        i3 = i2;
                                    } else {
                                        bVar2.close();
                                        i2 = 0;
                                        i3 = 0;
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    i2 = 0;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                bVar = bVar2;
                                if (bVar != null) {
                                    bVar.close();
                                }
                                throw th;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            i2 = 0;
                            bVar2 = null;
                        } catch (Throwable th6) {
                            th = th6;
                            bVar = null;
                        }
                        if (i3 != 0) {
                            return i3;
                        }
                        bVar4 = new b(bArr4);
                        ByteOrder byteOrderQ3 = q(bVar4);
                        this.f1154e = byteOrderQ3;
                        bVar4.f1112f = byteOrderQ3;
                        s2 = bVar4.readShort();
                        if (s2 != 20306 || s2 == 21330) {
                            i4 = 1;
                        } else {
                            i4 = i2;
                        }
                        bVar4.close();
                        if (i4 != 0) {
                            return 7;
                        }
                        bVar7 = new b(bArr4);
                        ByteOrder byteOrderQ4 = q(bVar7);
                        this.f1154e = byteOrderQ4;
                        bVar7.f1112f = byteOrderQ4;
                        if (bVar7.readShort() == 85) {
                            i5 = 1;
                        } else {
                            i5 = i2;
                        }
                        bVar7.close();
                        if (i5 != 0) {
                            return 10;
                        }
                        i6 = i2;
                        while (true) {
                            bArr = f1146w;
                            if (i6 < bArr.length) {
                                i7 = 1;
                                break;
                            }
                            if (bArr4[i6] != bArr[i6]) {
                                i7 = i2;
                                break;
                            }
                            i6++;
                        }
                        if (i7 != 0) {
                            return 13;
                        }
                        i8 = i2;
                        while (true) {
                            bArr2 = f1148y;
                            if (i8 < bArr2.length) {
                                i9 = i2;
                                while (true) {
                                    bArr3 = f1149z;
                                    if (i9 >= bArr3.length) {
                                        break;
                                        break;
                                    }
                                    if (bArr4[bArr2.length + i9 + 4] != bArr3[i9]) {
                                        break;
                                        break;
                                    }
                                    i9++;
                                }
                                if (i12 != 0) {
                                    return 14;
                                }
                                return i2;
                            }
                            if (bArr4[i8] != bArr2[i8]) {
                                break;
                                break;
                            }
                            i8++;
                        }
                        i12 = i2;
                        if (i12 != 0) {
                            return 14;
                        }
                        return i2;
                    }
                }
                return 9;
            }
            i10++;
        }
    }

    public final void g(f fVar) throws Throwable {
        int i2;
        int i3;
        j(fVar);
        HashMap[] mapArr = this.f1152c;
        c cVar = (c) mapArr[1].get("MakerNote");
        if (cVar != null) {
            f fVar2 = new f(cVar.f1118d);
            fVar2.f1112f = this.f1154e;
            byte[] bArr = f1145u;
            byte[] bArr2 = new byte[bArr.length];
            fVar2.readFully(bArr2);
            fVar2.b(0L);
            byte[] bArr3 = v;
            byte[] bArr4 = new byte[bArr3.length];
            fVar2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                fVar2.b(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                fVar2.b(12L);
            }
            s(fVar2, 6);
            c cVar2 = (c) mapArr[7].get("PreviewImageStart");
            c cVar3 = (c) mapArr[7].get("PreviewImageLength");
            if (cVar2 != null && cVar3 != null) {
                mapArr[5].put("JPEGInterchangeFormat", cVar2);
                mapArr[5].put("JPEGInterchangeFormatLength", cVar3);
            }
            c cVar4 = (c) mapArr[8].get("AspectFrame");
            if (cVar4 != null) {
                int[] iArr = (int[]) cVar4.g(this.f1154e);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i4 = iArr[2];
                int i5 = iArr[0];
                if (i4 <= i5 || (i2 = iArr[3]) <= (i3 = iArr[1])) {
                    return;
                }
                int i6 = (i4 - i5) + 1;
                int i7 = (i2 - i3) + 1;
                if (i6 < i7) {
                    int i8 = i6 + i7;
                    i7 = i8 - i7;
                    i6 = i8 - i7;
                }
                c cVarC = c.c(i6, this.f1154e);
                c cVarC2 = c.c(i7, this.f1154e);
                mapArr[0].put("ImageWidth", cVarC);
                mapArr[0].put("ImageLength", cVarC2);
            }
        }
    }

    public final void h(b bVar) throws Throwable {
        if (f1137l) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + bVar);
        }
        bVar.f1112f = ByteOrder.BIG_ENDIAN;
        int i2 = bVar.f1111e;
        bVar.a(f1146w.length);
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (z2 && z3) {
                return;
            }
            try {
                int i3 = bVar.readInt();
                int i4 = bVar.readInt();
                int i5 = bVar.f1111e;
                int i6 = i5 + i3 + 4;
                int i7 = i5 - i2;
                if (i7 == 16 && i4 != 1229472850) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appear as the first chunk");
                }
                if (i4 == 1229278788) {
                    return;
                }
                if (i4 == 1700284774 && !z2) {
                    this.f1156g = i7;
                    byte[] bArr = new byte[i3];
                    bVar.readFully(bArr);
                    int i8 = bVar.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(i4 >>> 24);
                    crc32.update(i4 >>> 16);
                    crc32.update(i4 >>> 8);
                    crc32.update(i4);
                    crc32.update(bArr);
                    if (((int) crc32.getValue()) != i8) {
                        throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + i8 + ", calculated CRC value: " + crc32.getValue());
                    }
                    r(bArr, 0);
                    x();
                    u(new b(bArr));
                    z2 = true;
                } else if (i4 == 1767135348 && !z3) {
                    byte[] bArr2 = f1147x;
                    if (i3 >= bArr2.length) {
                        int length = bArr2.length;
                        byte[] bArr3 = new byte[length];
                        bVar.readFully(bArr3);
                        if (Arrays.equals(bArr3, bArr2)) {
                            int i9 = bVar.f1111e - i2;
                            int i10 = i3 - length;
                            byte[] bArr4 = new byte[i10];
                            bVar.readFully(bArr4);
                            this.f1160k = new c(i9, bArr4, 1, i10);
                            z3 = true;
                        }
                    }
                }
                bVar.a(i6 - bVar.f1111e);
            } catch (EOFException e2) {
                throw new IOException("Encountered corrupt PNG file.", e2);
            }
        }
    }

    public final void i(b bVar) throws Throwable {
        boolean z2 = f1137l;
        if (z2) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + bVar);
        }
        bVar.a(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        bVar.readFully(bArr);
        bVar.readFully(bArr2);
        bVar.readFully(bArr3);
        int i2 = ByteBuffer.wrap(bArr).getInt();
        int i3 = ByteBuffer.wrap(bArr2).getInt();
        int i4 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i3];
        bVar.a(i2 - bVar.f1111e);
        bVar.readFully(bArr4);
        e(new b(bArr4), i2, 5);
        bVar.a(i4 - bVar.f1111e);
        bVar.f1112f = ByteOrder.BIG_ENDIAN;
        int i5 = bVar.readInt();
        if (z2) {
            Log.d("ExifInterface", "numberOfDirectoryEntry: " + i5);
        }
        for (int i6 = 0; i6 < i5; i6++) {
            int unsignedShort = bVar.readUnsignedShort();
            int unsignedShort2 = bVar.readUnsignedShort();
            if (unsignedShort == E.f1119a) {
                short s2 = bVar.readShort();
                short s3 = bVar.readShort();
                c cVarC = c.c(s2, this.f1154e);
                c cVarC2 = c.c(s3, this.f1154e);
                HashMap[] mapArr = this.f1152c;
                mapArr[0].put("ImageLength", cVarC);
                mapArr[0].put("ImageWidth", cVarC2);
                if (z2) {
                    Log.d("ExifInterface", "Updated to length: " + ((int) s2) + ", width: " + ((int) s3));
                    return;
                }
                return;
            }
            bVar.a(unsignedShort2);
        }
    }

    public final void j(f fVar) throws Throwable {
        o(fVar);
        s(fVar, 0);
        w(fVar, 0);
        w(fVar, 5);
        w(fVar, 4);
        x();
        if (this.f1151b == 8) {
            HashMap[] mapArr = this.f1152c;
            c cVar = (c) mapArr[1].get("MakerNote");
            if (cVar != null) {
                f fVar2 = new f(cVar.f1118d);
                fVar2.f1112f = this.f1154e;
                fVar2.a(6);
                s(fVar2, 9);
                c cVar2 = (c) mapArr[9].get("ColorSpace");
                if (cVar2 != null) {
                    mapArr[1].put("ColorSpace", cVar2);
                }
            }
        }
    }

    public final void k(f fVar) throws Throwable {
        if (f1137l) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + fVar);
        }
        j(fVar);
        HashMap[] mapArr = this.f1152c;
        c cVar = (c) mapArr[0].get("JpgFromRaw");
        if (cVar != null) {
            e(new b(cVar.f1118d), (int) cVar.f1117c, 5);
        }
        c cVar2 = (c) mapArr[0].get("ISO");
        c cVar3 = (c) mapArr[1].get("PhotographicSensitivity");
        if (cVar2 == null || cVar3 != null) {
            return;
        }
        mapArr[1].put("PhotographicSensitivity", cVar2);
    }

    public final void l(b bVar) throws Throwable {
        if (f1137l) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + bVar);
        }
        bVar.f1112f = ByteOrder.LITTLE_ENDIAN;
        bVar.a(f1148y.length);
        int i2 = bVar.readInt() + 8;
        byte[] bArr = f1149z;
        bVar.a(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                bVar.readFully(bArr2);
                int i3 = bVar.readInt();
                int i4 = length + 8;
                if (Arrays.equals(f1125A, bArr2)) {
                    byte[] bArrCopyOfRange = new byte[i3];
                    bVar.readFully(bArrCopyOfRange);
                    byte[] bArr3 = f1135M;
                    if (a.O(bArrCopyOfRange, bArr3)) {
                        bArrCopyOfRange = Arrays.copyOfRange(bArrCopyOfRange, bArr3.length, i3);
                    }
                    this.f1156g = i4;
                    r(bArrCopyOfRange, 0);
                    u(new b(bArrCopyOfRange));
                    return;
                }
                if (i3 % 2 == 1) {
                    i3++;
                }
                length = i4 + i3;
                if (length == i2) {
                    return;
                }
                if (length > i2) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                bVar.a(i3);
            } catch (EOFException e2) {
                throw new IOException("Encountered corrupt WebP file.", e2);
            }
        }
    }

    public final void m(b bVar, HashMap map) throws Throwable {
        c cVar = (c) map.get("JPEGInterchangeFormat");
        c cVar2 = (c) map.get("JPEGInterchangeFormatLength");
        if (cVar == null || cVar2 == null) {
            return;
        }
        int iE = cVar.e(this.f1154e);
        int iE2 = cVar2.e(this.f1154e);
        if (this.f1151b == 7) {
            iE += this.f1157h;
        }
        if (iE > 0 && iE2 > 0 && this.f1150a == null) {
            bVar.a(iE);
            bVar.readFully(new byte[iE2]);
        }
        if (f1137l) {
            Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + iE + ", length: " + iE2);
        }
    }

    public final boolean n(HashMap map) {
        c cVar = (c) map.get("ImageLength");
        c cVar2 = (c) map.get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.e(this.f1154e) <= 512 && cVar2.e(this.f1154e) <= 512;
    }

    public final void o(f fVar) throws IOException {
        ByteOrder byteOrderQ = q(fVar);
        this.f1154e = byteOrderQ;
        fVar.f1112f = byteOrderQ;
        int unsignedShort = fVar.readUnsignedShort();
        int i2 = this.f1151b;
        if (i2 != 7 && i2 != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i3 = fVar.readInt();
        if (i3 < 8) {
            throw new IOException("Invalid first Ifd offset: " + i3);
        }
        int i4 = i3 - 8;
        if (i4 > 0) {
            fVar.a(i4);
        }
    }

    public final void p() {
        int i2 = 0;
        while (true) {
            HashMap[] mapArr = this.f1152c;
            if (i2 >= mapArr.length) {
                return;
            }
            Log.d("ExifInterface", "The size of tag group[" + i2 + "]: " + mapArr[i2].size());
            for (Map.Entry entry : mapArr[i2].entrySet()) {
                c cVar = (c) entry.getValue();
                Log.d("ExifInterface", "tagName: " + ((String) entry.getKey()) + ", tagType: " + cVar.toString() + ", tagValue: '" + cVar.f(this.f1154e) + "'");
            }
            i2++;
        }
    }

    public final void r(byte[] bArr, int i2) throws IOException {
        f fVar = new f(bArr);
        o(fVar);
        s(fVar, i2);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0210  */
    /* JADX WARN: Code duplicated, block: B:103:0x0214  */
    /* JADX WARN: Code duplicated, block: B:108:0x0221  */
    /* JADX WARN: Code duplicated, block: B:109:0x0226  */
    /* JADX WARN: Code duplicated, block: B:110:0x0232  */
    /* JADX WARN: Code duplicated, block: B:112:0x0239  */
    /* JADX WARN: Code duplicated, block: B:115:0x0253 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:119:0x025b  */
    /* JADX WARN: Code duplicated, block: B:127:0x0299  */
    /* JADX WARN: Code duplicated, block: B:129:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:132:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:134:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:137:0x0301  */
    /* JADX WARN: Code duplicated, block: B:139:0x0309  */
    /* JADX WARN: Code duplicated, block: B:148:0x0333  */
    /* JADX WARN: Code duplicated, block: B:175:0x0336 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x014f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0158  */
    /* JADX WARN: Code duplicated, block: B:74:0x0160  */
    /* JADX WARN: Code duplicated, block: B:76:0x0166  */
    /* JADX WARN: Code duplicated, block: B:77:0x017a  */
    /* JADX WARN: Code duplicated, block: B:80:0x0181  */
    /* JADX WARN: Code duplicated, block: B:82:0x018b  */
    /* JADX WARN: Code duplicated, block: B:83:0x018d  */
    /* JADX WARN: Code duplicated, block: B:84:0x0191  */
    /* JADX WARN: Code duplicated, block: B:86:0x0194  */
    /* JADX WARN: Code duplicated, block: B:90:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:93:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:95:0x0206  */
    /* JADX WARN: Code duplicated, block: B:97:0x0209  */
    /* JADX WARN: Code duplicated, block: B:99:0x020c  */
    /* JADX WARN: Instruction removed from duplicated block: B:127:0x0299, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:129:0x02a9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:76:0x0166, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:93:0x01eb, please report this as an issue */
    public final void s(f fVar, int i2) throws IOException {
        HashMap[] mapArr;
        long j2;
        long j3;
        boolean z2;
        int i3;
        long j4;
        Integer num;
        HashSet hashSet;
        long j5;
        String str;
        int unsignedShort;
        long j6;
        String str2;
        int i4;
        int i5 = fVar.f1111e;
        int i6 = fVar.f1114h;
        Integer numValueOf = Integer.valueOf(i5);
        HashSet hashSet2 = this.f1153d;
        hashSet2.add(numValueOf);
        short s2 = fVar.readShort();
        boolean z3 = f1137l;
        if (z3) {
            Log.d("ExifInterface", "numberOfDirectoryEntry: " + ((int) s2));
        }
        if (s2 <= 0) {
            return;
        }
        short s3 = 0;
        while (true) {
            mapArr = this.f1152c;
            if (s3 >= s2) {
                break;
            }
            int unsignedShort2 = fVar.readUnsignedShort();
            int unsignedShort3 = fVar.readUnsignedShort();
            int i7 = fVar.readInt();
            long j7 = ((long) fVar.f1111e) + 4;
            short s4 = s2;
            d dVar = (d) f1130H[i2].get(Integer.valueOf(unsignedShort2));
            if (z3) {
                Log.d("ExifInterface", String.format("ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d", Integer.valueOf(i2), Integer.valueOf(unsignedShort2), dVar != null ? dVar.f1120b : null, Integer.valueOf(unsignedShort3), Integer.valueOf(i7)));
            }
            if (dVar != null) {
                if (unsignedShort3 > 0) {
                    int[] iArr = f1126C;
                    if (unsignedShort3 < iArr.length) {
                        int i8 = dVar.f1121c;
                        if (i8 == 7 || unsignedShort3 == 7 || i8 == unsignedShort3 || (i3 = dVar.f1122d) == unsignedShort3 || (((i8 == 4 || i3 == 4) && unsignedShort3 == 3) || (((i8 == 9 || i3 == 9) && unsignedShort3 == 8) || ((i8 == 12 || i3 == 12) && unsignedShort3 == 11)))) {
                            if (unsignedShort3 == 7) {
                                unsignedShort3 = i8;
                            }
                            j2 = j7;
                            j3 = ((long) i7) * ((long) iArr[unsignedShort3]);
                            if (j3 < 0 || j3 > 2147483647L) {
                                if (z3 != 0) {
                                    Log.d("ExifInterface", "Skip the tag entry since the number of components is invalid: " + i7);
                                }
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                        } else if (z3 != 0) {
                            Log.d("ExifInterface", "Skip the tag entry since data format (" + B[unsignedShort3] + ") is unexpected for tag: " + dVar.f1120b);
                        }
                    }
                    if (z2) {
                        j4 = j2;
                        if (j3 > 4) {
                            i4 = fVar.readInt();
                            if (z3 != 0) {
                                Log.d("ExifInterface", "seek to data offset: " + i4);
                            }
                            if (this.f1151b == 7) {
                                if ("MakerNote".equals(dVar.f1120b)) {
                                    this.f1157h = i4;
                                } else if (i2 != 6 && "ThumbnailImage".equals(dVar.f1120b)) {
                                    this.f1158i = i4;
                                    this.f1159j = i7;
                                    c cVarC = c.c(6, this.f1154e);
                                    c cVarA = c.a(this.f1158i, this.f1154e);
                                    c cVarA2 = c.a(this.f1159j, this.f1154e);
                                    mapArr[4].put("Compression", cVarC);
                                    mapArr[4].put("JPEGInterchangeFormat", cVarA);
                                    mapArr[4].put("JPEGInterchangeFormatLength", cVarA2);
                                }
                            }
                            fVar.b(i4);
                        } else {
                            j4 = j4;
                            unsignedShort2 = unsignedShort2;
                            dVar = dVar;
                        }
                        num = (Integer) f1133K.get(Integer.valueOf(unsignedShort2));
                        if (z3 != 0) {
                            Log.d("ExifInterface", "nextIfdType: " + num + " byteCount: " + j3);
                        }
                        if (num != null) {
                            if (unsignedShort3 != 3) {
                                if (unsignedShort3 == 4) {
                                    j6 = ((long) fVar.readInt()) & 4294967295L;
                                } else if (unsignedShort3 == 8) {
                                    unsignedShort = fVar.readShort();
                                } else if (unsignedShort3 != 9 || unsignedShort3 == 13) {
                                    unsignedShort = fVar.readInt();
                                } else {
                                    j6 = -1;
                                }
                                if (z3 != 0) {
                                    Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                                }
                                if (j6 > 0 || (i6 != -1 && j6 >= i6)) {
                                    hashSet = hashSet2;
                                    if (z3 != 0) {
                                        str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                        if (i6 != -1) {
                                            str2 = str2 + " (total length: " + i6 + ")";
                                        }
                                        Log.d("ExifInterface", str2);
                                    }
                                } else {
                                    hashSet = hashSet2;
                                    if (!hashSet.contains(Integer.valueOf((int) j6))) {
                                        fVar.b(j6);
                                        s(fVar, num.intValue());
                                    } else if (z3 != 0) {
                                        Log.d("ExifInterface", "Skip jump into the IFD since it has already been read: IfdType " + num + " (at " + j6 + ")");
                                    }
                                }
                                fVar.b(j4);
                            } else {
                                unsignedShort = fVar.readUnsignedShort();
                            }
                            j6 = unsignedShort;
                            if (z3 != 0) {
                                Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                            }
                            if (j6 > 0) {
                                hashSet = hashSet2;
                                if (z3 != 0) {
                                    str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                    if (i6 != -1) {
                                        str2 = str2 + " (total length: " + i6 + ")";
                                    }
                                    Log.d("ExifInterface", str2);
                                }
                            } else {
                                hashSet = hashSet2;
                                if (z3 != 0) {
                                    str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                    if (i6 != -1) {
                                        str2 = str2 + " (total length: " + i6 + ")";
                                    }
                                    Log.d("ExifInterface", str2);
                                }
                            }
                            fVar.b(j4);
                        } else {
                            hashSet = hashSet2;
                            j5 = j4;
                            int i9 = fVar.f1111e + this.f1156g;
                            byte[] bArr = new byte[(int) j3];
                            fVar.readFully(bArr);
                            c cVar = new c(i9, bArr, unsignedShort3, i7);
                            HashMap map = mapArr[i2];
                            str = dVar.f1120b;
                            map.put(str, cVar);
                            if ("DNGVersion".equals(str)) {
                                this.f1151b = 3;
                            }
                            if (((!"Make".equals(str) || "Model".equals(str)) && cVar.f(this.f1154e).contains("PENTAX")) || ("Compression".equals(str) && cVar.e(this.f1154e) == 65535)) {
                                this.f1151b = 8;
                            }
                            if (fVar.f1111e != j5) {
                                fVar.b(j5);
                            }
                        }
                    } else {
                        fVar.b(j2);
                        hashSet = hashSet2;
                    }
                    s3 = (short) (s3 + 1);
                    hashSet2 = hashSet;
                    s2 = s4;
                    z3 = z3;
                }
                j2 = j7;
                if (z3 != 0) {
                    Log.d("ExifInterface", "Skip the tag entry since data format is invalid: " + unsignedShort3);
                }
                j3 = 0;
                z2 = false;
                if (z2) {
                    fVar.b(j2);
                    hashSet = hashSet2;
                } else {
                    j4 = j2;
                    if (j3 > 4) {
                        i4 = fVar.readInt();
                        if (z3 != 0) {
                            Log.d("ExifInterface", "seek to data offset: " + i4);
                        }
                        if (this.f1151b == 7) {
                            if ("MakerNote".equals(dVar.f1120b)) {
                                this.f1157h = i4;
                            } else if (i2 != 6) {
                            }
                        }
                        fVar.b(i4);
                    } else {
                        j4 = j4;
                        unsignedShort2 = unsignedShort2;
                        dVar = dVar;
                    }
                    num = (Integer) f1133K.get(Integer.valueOf(unsignedShort2));
                    if (z3 != 0) {
                        Log.d("ExifInterface", "nextIfdType: " + num + " byteCount: " + j3);
                    }
                    if (num != null) {
                        if (unsignedShort3 != 3) {
                            if (unsignedShort3 == 4) {
                                j6 = ((long) fVar.readInt()) & 4294967295L;
                            } else if (unsignedShort3 == 8) {
                                if (unsignedShort3 != 9) {
                                }
                                unsignedShort = fVar.readInt();
                            } else {
                                unsignedShort = fVar.readShort();
                            }
                            if (z3 != 0) {
                                Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                            }
                            if (j6 > 0) {
                                hashSet = hashSet2;
                                if (z3 != 0) {
                                    str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                    if (i6 != -1) {
                                        str2 = str2 + " (total length: " + i6 + ")";
                                    }
                                    Log.d("ExifInterface", str2);
                                }
                            } else {
                                hashSet = hashSet2;
                                if (z3 != 0) {
                                    str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                    if (i6 != -1) {
                                        str2 = str2 + " (total length: " + i6 + ")";
                                    }
                                    Log.d("ExifInterface", str2);
                                }
                            }
                            fVar.b(j4);
                        } else {
                            unsignedShort = fVar.readUnsignedShort();
                        }
                        j6 = unsignedShort;
                        if (z3 != 0) {
                            Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                        }
                        if (j6 > 0) {
                            hashSet = hashSet2;
                            if (z3 != 0) {
                                str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                if (i6 != -1) {
                                    str2 = str2 + " (total length: " + i6 + ")";
                                }
                                Log.d("ExifInterface", str2);
                            }
                        } else {
                            hashSet = hashSet2;
                            if (z3 != 0) {
                                str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                if (i6 != -1) {
                                    str2 = str2 + " (total length: " + i6 + ")";
                                }
                                Log.d("ExifInterface", str2);
                            }
                        }
                        fVar.b(j4);
                    } else {
                        hashSet = hashSet2;
                        j5 = j4;
                        int i10 = fVar.f1111e + this.f1156g;
                        byte[] bArr2 = new byte[(int) j3];
                        fVar.readFully(bArr2);
                        c cVar2 = new c(i10, bArr2, unsignedShort3, i7);
                        HashMap map2 = mapArr[i2];
                        str = dVar.f1120b;
                        map2.put(str, cVar2);
                        if ("DNGVersion".equals(str)) {
                            this.f1151b = 3;
                        }
                        if (!"Make".equals(str)) {
                        }
                        this.f1151b = 8;
                        if (fVar.f1111e != j5) {
                            fVar.b(j5);
                        }
                    }
                }
                s3 = (short) (s3 + 1);
                hashSet2 = hashSet;
                s2 = s4;
                z3 = z3;
            } else if (z3) {
                Log.d("ExifInterface", "Skip the tag entry since tag number is not defined: " + unsignedShort2);
            }
            j2 = j7;
            j3 = 0;
            z2 = false;
            if (z2) {
                fVar.b(j2);
                hashSet = hashSet2;
            } else {
                j4 = j2;
                if (j3 > 4) {
                    i4 = fVar.readInt();
                    if (z3 != 0) {
                        Log.d("ExifInterface", "seek to data offset: " + i4);
                    }
                    if (this.f1151b == 7) {
                        if ("MakerNote".equals(dVar.f1120b)) {
                            this.f1157h = i4;
                        } else if (i2 != 6) {
                        }
                    }
                    fVar.b(i4);
                } else {
                    j4 = j4;
                    unsignedShort2 = unsignedShort2;
                    dVar = dVar;
                }
                num = (Integer) f1133K.get(Integer.valueOf(unsignedShort2));
                if (z3 != 0) {
                    Log.d("ExifInterface", "nextIfdType: " + num + " byteCount: " + j3);
                }
                if (num != null) {
                    if (unsignedShort3 != 3) {
                        if (unsignedShort3 == 4) {
                            j6 = ((long) fVar.readInt()) & 4294967295L;
                        } else if (unsignedShort3 == 8) {
                            if (unsignedShort3 != 9) {
                            }
                            unsignedShort = fVar.readInt();
                        } else {
                            unsignedShort = fVar.readShort();
                        }
                        if (z3 != 0) {
                            Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                        }
                        if (j6 > 0) {
                            hashSet = hashSet2;
                            if (z3 != 0) {
                                str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                if (i6 != -1) {
                                    str2 = str2 + " (total length: " + i6 + ")";
                                }
                                Log.d("ExifInterface", str2);
                            }
                        } else {
                            hashSet = hashSet2;
                            if (z3 != 0) {
                                str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                                if (i6 != -1) {
                                    str2 = str2 + " (total length: " + i6 + ")";
                                }
                                Log.d("ExifInterface", str2);
                            }
                        }
                        fVar.b(j4);
                    } else {
                        unsignedShort = fVar.readUnsignedShort();
                    }
                    j6 = unsignedShort;
                    if (z3 != 0) {
                        Log.d("ExifInterface", String.format("Offset: %d, tagName: %s", Long.valueOf(j6), dVar.f1120b));
                    }
                    if (j6 > 0) {
                        hashSet = hashSet2;
                        if (z3 != 0) {
                            str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                            if (i6 != -1) {
                                str2 = str2 + " (total length: " + i6 + ")";
                            }
                            Log.d("ExifInterface", str2);
                        }
                    } else {
                        hashSet = hashSet2;
                        if (z3 != 0) {
                            str2 = "Skip jump into the IFD since its offset is invalid: " + j6;
                            if (i6 != -1) {
                                str2 = str2 + " (total length: " + i6 + ")";
                            }
                            Log.d("ExifInterface", str2);
                        }
                    }
                    fVar.b(j4);
                } else {
                    hashSet = hashSet2;
                    j5 = j4;
                    int i11 = fVar.f1111e + this.f1156g;
                    byte[] bArr3 = new byte[(int) j3];
                    fVar.readFully(bArr3);
                    c cVar3 = new c(i11, bArr3, unsignedShort3, i7);
                    HashMap map3 = mapArr[i2];
                    str = dVar.f1120b;
                    map3.put(str, cVar3);
                    if ("DNGVersion".equals(str)) {
                        this.f1151b = 3;
                    }
                    if (!"Make".equals(str)) {
                    }
                    this.f1151b = 8;
                    if (fVar.f1111e != j5) {
                        fVar.b(j5);
                    }
                }
            }
            s3 = (short) (s3 + 1);
            hashSet2 = hashSet;
            s2 = s4;
            z3 = z3;
        }
        HashSet hashSet3 = hashSet2;
        boolean z4 = z3;
        int i12 = fVar.readInt();
        if (z4) {
            Log.d("ExifInterface", String.format("nextIfdOffset: %d", Integer.valueOf(i12)));
        }
        long j8 = i12;
        if (j8 <= 0) {
            if (z4) {
                Log.d("ExifInterface", "Stop reading file since a wrong offset may cause an infinite loop: " + i12);
                return;
            }
            return;
        }
        if (hashSet3.contains(Integer.valueOf(i12))) {
            if (z4) {
                Log.d("ExifInterface", "Stop reading file since re-reading an IFD may cause an infinite loop: " + i12);
                return;
            }
            return;
        }
        fVar.b(j8);
        if (mapArr[4].isEmpty()) {
            s(fVar, 4);
        } else if (mapArr[5].isEmpty()) {
            s(fVar, 5);
        }
    }

    public final void t(int i2, String str, String str2) {
        HashMap[] mapArr = this.f1152c;
        if (mapArr[i2].isEmpty() || mapArr[i2].get(str) == null) {
            return;
        }
        HashMap map = mapArr[i2];
        map.put(str2, (c) map.get(str));
        mapArr[i2].remove(str);
    }

    public final void u(b bVar) throws Throwable {
        c cVar;
        int iE;
        HashMap map = this.f1152c[4];
        c cVar2 = (c) map.get("Compression");
        if (cVar2 == null) {
            m(bVar, map);
            return;
        }
        int iE2 = cVar2.e(this.f1154e);
        if (iE2 != 1) {
            if (iE2 == 6) {
                m(bVar, map);
                return;
            } else if (iE2 != 7) {
                return;
            }
        }
        c cVar3 = (c) map.get("BitsPerSample");
        if (cVar3 != null) {
            int[] iArr = (int[]) cVar3.g(this.f1154e);
            int[] iArr2 = m;
            if (Arrays.equals(iArr2, iArr) || (this.f1151b == 3 && (cVar = (c) map.get("PhotometricInterpretation")) != null && (((iE = cVar.e(this.f1154e)) == 1 && Arrays.equals(iArr, f1138n)) || (iE == 6 && Arrays.equals(iArr, iArr2))))) {
                c cVar4 = (c) map.get("StripOffsets");
                c cVar5 = (c) map.get("StripByteCounts");
                if (cVar4 == null || cVar5 == null) {
                    return;
                }
                long[] jArrJ = a.j(cVar4.g(this.f1154e));
                long[] jArrJ2 = a.j(cVar5.g(this.f1154e));
                if (jArrJ == null || jArrJ.length == 0) {
                    Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
                    return;
                }
                if (jArrJ2 == null || jArrJ2.length == 0) {
                    Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
                    return;
                }
                if (jArrJ.length != jArrJ2.length) {
                    Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
                    return;
                }
                long j2 = 0;
                for (long j3 : jArrJ2) {
                    j2 += j3;
                }
                byte[] bArr = new byte[(int) j2];
                this.f1155f = true;
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < jArrJ.length; i4++) {
                    int i5 = (int) jArrJ[i4];
                    int i6 = (int) jArrJ2[i4];
                    if (i4 < jArrJ.length - 1 && i5 + i6 != jArrJ[i4 + 1]) {
                        this.f1155f = false;
                    }
                    int i7 = i5 - i2;
                    if (i7 < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                        return;
                    }
                    try {
                        bVar.a(i7);
                        int i8 = i2 + i7;
                        byte[] bArr2 = new byte[i6];
                        try {
                            bVar.readFully(bArr2);
                            i2 = i8 + i6;
                            System.arraycopy(bArr2, 0, bArr, i3, i6);
                            i3 += i6;
                        } catch (EOFException unused) {
                            Log.d("ExifInterface", "Failed to read " + i6 + " bytes.");
                            return;
                        }
                    } catch (EOFException unused2) {
                        Log.d("ExifInterface", "Failed to skip " + i7 + " bytes.");
                        return;
                    }
                }
                if (this.f1155f) {
                    long j4 = jArrJ[0];
                    return;
                }
                return;
            }
        }
        if (f1137l) {
            Log.d("ExifInterface", "Unsupported data type value");
        }
    }

    public final void v(int i2, int i3) throws Throwable {
        HashMap[] mapArr = this.f1152c;
        boolean zIsEmpty = mapArr[i2].isEmpty();
        boolean z2 = f1137l;
        if (zIsEmpty || mapArr[i3].isEmpty()) {
            if (z2) {
                Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
                return;
            }
            return;
        }
        c cVar = (c) mapArr[i2].get("ImageLength");
        c cVar2 = (c) mapArr[i2].get("ImageWidth");
        c cVar3 = (c) mapArr[i3].get("ImageLength");
        c cVar4 = (c) mapArr[i3].get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            if (z2) {
                Log.d("ExifInterface", "First image does not contain valid size information");
                return;
            }
            return;
        }
        if (cVar3 == null || cVar4 == null) {
            if (z2) {
                Log.d("ExifInterface", "Second image does not contain valid size information");
                return;
            }
            return;
        }
        int iE = cVar.e(this.f1154e);
        int iE2 = cVar2.e(this.f1154e);
        int iE3 = cVar3.e(this.f1154e);
        int iE4 = cVar4.e(this.f1154e);
        if (iE >= iE3 || iE2 >= iE4) {
            return;
        }
        HashMap map = mapArr[i2];
        mapArr[i2] = mapArr[i3];
        mapArr[i3] = map;
    }

    public final void w(f fVar, int i2) throws Throwable {
        c cVarC;
        c cVarC2;
        HashMap[] mapArr = this.f1152c;
        c cVar = (c) mapArr[i2].get("DefaultCropSize");
        c cVar2 = (c) mapArr[i2].get("SensorTopBorder");
        c cVar3 = (c) mapArr[i2].get("SensorLeftBorder");
        c cVar4 = (c) mapArr[i2].get("SensorBottomBorder");
        c cVar5 = (c) mapArr[i2].get("SensorRightBorder");
        if (cVar != null) {
            if (cVar.f1115a == 5) {
                e[] eVarArr = (e[]) cVar.g(this.f1154e);
                if (eVarArr == null || eVarArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(eVarArr));
                    return;
                }
                cVarC = c.b(eVarArr[0], this.f1154e);
                cVarC2 = c.b(eVarArr[1], this.f1154e);
            } else {
                int[] iArr = (int[]) cVar.g(this.f1154e);
                if (iArr == null || iArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
                cVarC = c.c(iArr[0], this.f1154e);
                cVarC2 = c.c(iArr[1], this.f1154e);
            }
            mapArr[i2].put("ImageWidth", cVarC);
            mapArr[i2].put("ImageLength", cVarC2);
            return;
        }
        if (cVar2 != null && cVar3 != null && cVar4 != null && cVar5 != null) {
            int iE = cVar2.e(this.f1154e);
            int iE2 = cVar4.e(this.f1154e);
            int iE3 = cVar5.e(this.f1154e);
            int iE4 = cVar3.e(this.f1154e);
            if (iE2 <= iE || iE3 <= iE4) {
                return;
            }
            c cVarC3 = c.c(iE2 - iE, this.f1154e);
            c cVarC4 = c.c(iE3 - iE4, this.f1154e);
            mapArr[i2].put("ImageLength", cVarC3);
            mapArr[i2].put("ImageWidth", cVarC4);
            return;
        }
        c cVar6 = (c) mapArr[i2].get("ImageLength");
        c cVar7 = (c) mapArr[i2].get("ImageWidth");
        if (cVar6 == null || cVar7 == null) {
            c cVar8 = (c) mapArr[i2].get("JPEGInterchangeFormat");
            c cVar9 = (c) mapArr[i2].get("JPEGInterchangeFormatLength");
            if (cVar8 == null || cVar9 == null) {
                return;
            }
            int iE5 = cVar8.e(this.f1154e);
            int iE6 = cVar8.e(this.f1154e);
            fVar.b(iE5);
            byte[] bArr = new byte[iE6];
            fVar.readFully(bArr);
            e(new b(bArr), iE5, i2);
        }
    }

    public final void x() throws Throwable {
        v(0, 5);
        v(0, 4);
        v(5, 4);
        HashMap[] mapArr = this.f1152c;
        c cVar = (c) mapArr[1].get("PixelXDimension");
        c cVar2 = (c) mapArr[1].get("PixelYDimension");
        if (cVar != null && cVar2 != null) {
            mapArr[0].put("ImageWidth", cVar);
            mapArr[0].put("ImageLength", cVar2);
        }
        if (mapArr[4].isEmpty() && n(mapArr[5])) {
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap();
        }
        if (!n(mapArr[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
        t(0, "ThumbnailOrientation", "Orientation");
        t(0, "ThumbnailImageLength", "ImageLength");
        t(0, "ThumbnailImageWidth", "ImageWidth");
        t(5, "ThumbnailOrientation", "Orientation");
        t(5, "ThumbnailImageLength", "ImageLength");
        t(5, "ThumbnailImageWidth", "ImageWidth");
        t(4, "Orientation", "ThumbnailOrientation");
        t(4, "ImageLength", "ThumbnailImageLength");
        t(4, "ImageWidth", "ThumbnailImageWidth");
    }
}
