//package com.rp.rahmawatiputrianasari.research00.utils;
//
///**
// * Created by rahmawatiputrianasari on 11/7/17.
// */
//
//import android.app.Activity;
//import android.app.ActivityManager;
//import android.app.AlarmManager;
//import android.app.Application;
//import android.app.DownloadManager;
//import android.app.PendingIntent;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.location.Address;
//import android.location.Geocoder;
//import android.media.MediaScannerConnection;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.content.FileProvider;
//import android.util.Base64;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//
///**
// * Created by norms on 10/7/16.
// */
//
//public class Utils {
//
//    public static final int REQUEST_IMAGE_CAPTURE = 111;
//    public static final int REQUEST_IMAGE_GALLERY = 222;
//    public static final int REQUEST_FILES = 333;
//    public static final int REQUEST_IMAGE_CAPTURE_CROP = 1111;
//    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
//    private static final int BUFFER = 80000;
//    private static Toast toast;
//
//    public static final String URI_CAPTURED_IMAGE = "URI_CAPTURED_IMAGE";
//
//    public static void openApplicationOnGooglePlay(Activity activity, String packageName) {
//        try {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("market://details?id=" + packageName));
//            if (intent.resolveActivity(activity.getPackageManager()) != null) {
//                activity.startActivity(intent);
//            } else {
//                Toast.makeText(activity, "No application exists to handle the request", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * Checking for all possible internet providers
//     */
//    public static boolean isOnline(Context context) {
//        if (context != null) {
//
//            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            if (connectivity != null) {
//                NetworkInfo[] info = connectivity.getAllNetworkInfo();
//                if (info != null)
//                    for (int i = 0; i < info.length; i++)
//                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                            return true;
//                        }
//            }
//        }
//
//        return false;
//    }
//
//    public static void slideDown(View v) {
//        if (v.isShown()) {
//            Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_down);
//            if (anim != null) {
//                anim.reset();
//                if (v != null) {
//                    v.clearAnimation();
//                    v.startAnimation(anim);
//                }
//            }
//        }
//    }
//
//    public static void slideUp(View v) {
//        if (!v.isShown()) {
//            Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_up);
//            if (anim != null) {
//                anim.reset();
//                if (v != null) {
//                    v.clearAnimation();
//                    v.startAnimation(anim);
//                }
//            }
//        }
//    }
//
//    public static void toggleContent(View view) {
//        if (view.isShown()) {
//            slideUp(view);
//            view.setVisibility(View.GONE);
//        } else {
//            slideDown(view);
//            view.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public static File bitmapToFile(Context context, Bitmap bitmap, String filePath, String fileName) {
//        File file = new File(filePath, fileName);
//        FileOutputStream fOut = null;
//        try {
//            fOut = new FileOutputStream(file);
//
//            if (file.length() <= Constants.MAX_SIZE) {
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
//            } else {
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//            }
//
//            fOut.flush();
//            fOut.close();
//        } catch (OutOfMemoryError e) {
//            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 2;
//                Bitmap newBit = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//
//                if (file.length() <= Constants.MAX_SIZE) {
//                    newBit.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
//                } else {
//                    newBit.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//                }
//
//                fOut.flush();
//                fOut.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    public static File bitmapToFile(Context context, Bitmap bitmap, String filePath) {
//        File file = new File(filePath);
//        FileOutputStream fOut = null;
//        try {
//            fOut = new FileOutputStream(file);
//
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    public static File compressImage(File file) {
//        FileOutputStream fOut = null;
//        try {
//            fOut = new FileOutputStream(file);
//
////            final BitmapFactory.Options options = new BitmapFactory.Options();
////            options.inSampleSize = 2;
////            options.inJustDecodeBounds = false;
////            SystemClock.sleep(3000);
//
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
//
//            fOut.flush();
//            fOut.close();
//        } catch (OutOfMemoryError e) {
//            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 2;
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
//                fOut.flush();
//                fOut.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        final BitmapFactory.Options options = new BitmapFactory.Options();
////        options.inJustDecodeBounds = true;
////        BufferedInputStream buffer = new FileOutputStream(file);
////        BitmapFactory.decodeStream(buffer, null, options);
////        buffer.reset();
////
////        // Calculate inSampleSize
////        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
////
////        // Decode bitmap with inSampleSize set
////        options.inJustDecodeBounds = false;
////        BitmapFactory.decodeStream(buffer, null, options);
//
//        return file;
//    }
//
//    public static void showToast(Context context, String message) {
//        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
//        toast.show();
//    }
//
//    public static void showSnackBar(View view, String message, boolean isLongDuration) {
//        try {
//            Snackbar snackbar = Snackbar.make(view, message,
//                    (isLongDuration) ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);
//            snackbar.show();
//        } catch (Exception ex) {
//            // do nothing
//        }
//
//    }
//
//    public static boolean isToastShown() {
//        if (toast != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static void loadImage(String url, ImageView imageView) {
//        try {
//            Glide.with(imageView.getContext())
//                    .load(url)
//                    .crossFade()
//                    .placeholder(R.drawable.temp_banner)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imageView);
//        } catch (Exception e) {
//            // do nothing
//        }
//
//    }
//
//    public static boolean saveImageToGallery(Context context, Bitmap bitmap, String filePath, String fileName) {
//        File sdCardDirectory = new File(Environment.getExternalStorageDirectory(), filePath);
//
//        if (!sdCardDirectory.exists()) {
//            sdCardDirectory.mkdirs();
//        }
//
//        File file = new File(sdCardDirectory, fileName);
//
//        FileOutputStream fOut;
//
//        try {
//            fOut = new FileOutputStream(file);
//
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//            fOut.flush();
//            fOut.close();
//            reloadGallery(context, file.getAbsolutePath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//    public static void reloadGallery(Context context, String file) {
//        MediaScannerConnection.scanFile(context,
//                new String[]{file}, null,
//                new MediaScannerConnection.OnScanCompletedListener() {
//                    public void onScanCompleted(String path, Uri uri) {
//                        LogUtil.debug("ExternalStorage, Scanned " + path + ":");
//                        LogUtil.debug("ExternalStorage, -> uri=" + uri);
//                    }
//                });
//    }
//
//    // promo with image
//    public static String[] getPendingNotification(int type) {
//        String[] promoNotification = new String[5];
//
//        switch (type) {
//            case Constants.PUSH_TYPE_INFO:
//                promoNotification[0] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_INFO, "");
//                promoNotification[1] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_INFO, "");
//                promoNotification[2] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_INFO, "");
//                promoNotification[3] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_INFO, "");
//
//                break;
//
//            case Constants.PUSH_TYPE_NOTIFICATION:
//                promoNotification[0] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_NOTIFICATION, "");
//                promoNotification[1] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_NOTIFICATION, "");
//                promoNotification[2] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_NOTIFICATION, "");
//                promoNotification[3] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_NOTIFICATION, "");
//
//                break;
//
//            case Constants.PUSH_TYPE_ADS:
//                promoNotification[0] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_ADS, "");
//                promoNotification[1] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_ADS, "");
//                promoNotification[2] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_ADS, "");
//                promoNotification[3] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_ADS, "");
//                promoNotification[4] = Prefs.getString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_PROMO_ADS, "");
//
//                break;
//
//            case Constants.PUSH_TYPE_VERSION:
//                // do nothing
//                break;
//
//            case Constants.PUSH_TYPE_LOCATION:
//                // do nothing
//                break;
//        }
//
//        return promoNotification;
//    }
//
//    public static void savePendingNotification(int type, String title, String content, String fileName, String promo) {
//
//        switch (type) {
//            case Constants.PUSH_TYPE_INFO:
//                if (title.isEmpty() && content.isEmpty() && fileName.isEmpty()) {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_INFO, false);
//                } else {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_INFO, true);
//                }
//
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_INFO, String.valueOf(type));
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_INFO, title);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_INFO, content);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_INFO, fileName);
//
//                break;
//
//            case Constants.PUSH_TYPE_NOTIFICATION:
//                if (title.isEmpty() && content.isEmpty() && fileName.isEmpty()) {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_NOTIFICATION, false);
//                } else {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_NOTIFICATION, true);
//                }
//
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_NOTIFICATION, String.valueOf(type));
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_NOTIFICATION, title);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_NOTIFICATION, content);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_NOTIFICATION, fileName);
//
//                break;
//
//            case Constants.PUSH_TYPE_ADS:
//                if (title.isEmpty() && content.isEmpty() && fileName.isEmpty()) {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_ADS, false);
//                } else {
//                    Prefs.putBoolean(Constants.PREFS_HAVE_PENDING_PUSH_ADS, true);
//                }
//
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TYPE_ADS, String.valueOf(type));
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_TITLE_ADS, title);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_CONTENT_ADS, content);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_PROMO_ADS, promo);
//                Prefs.putString(Constants.PREFS_KEY_PROMO_PENDING_PUSH_IMAGE_ADS, fileName);
//
//                break;
//
//            case Constants.PUSH_TYPE_VERSION:
//                // do nothing
//                break;
//
//            case Constants.PUSH_TYPE_LOCATION:
//                // do nothing
//                break;
//        }
//
//    }
//
//    public static void removePendingNotification(int type) {
//        savePendingNotification(type, "", "", "", "");
//    }
//
//    /**
//     * Unzip the file with fast performance, then delete it
//     * http://stackoverflow.com/questions/4504291/how-to-speed-up-unzipping-time-in-java-android/4504692#4504692
//     */
//    public static void unzip(File zipFile, String targetLocation) {
//        // create target location folder if not exist
//        dirChecker(targetLocation);
//
//        try {
//            FileInputStream fin = new FileInputStream(zipFile);
//            ZipInputStream zin = new ZipInputStream(fin);
//            ZipEntry ze = null;
//            while ((ze = zin.getNextEntry()) != null) {
//
//                // create dir if required while unzipping
//                if (ze.isDirectory()) {
//                    dirChecker(ze.getName());
//                } else {
//                    FileOutputStream fout = new FileOutputStream(
//                            targetLocation + "/" + ze.getName());
//                    BufferedOutputStream bufout = new BufferedOutputStream(fout);
//                    byte[] buffer = new byte[1024];
//                    int read = 0;
//                    while ((read = zin.read(buffer)) != -1) {
//                        bufout.write(buffer, 0, read);
//                    }
//
//                    zin.closeEntry();
////                    bufout.flush();
//                    bufout.close();
//                    fout.close();
//                }
//            }
//            zin.close();
//
//            zipFile.delete();
//        } catch (Exception e) {
//            LogUtil.error("Unable unzip the file: " + zipFile.getAbsolutePath() + " - " + e.getMessage());
//        }
//    }
//
//    /**
//     * Zip the file with fast performance
//     * http://stackoverflow.com/questions/4504291/how-to-speed-up-unzipping-time-in-java-android/4504692#4504692
//     */
//    public static void zip(String[] _files, String zipFileName) {
//        try {
//            BufferedInputStream origin = null;
//            FileOutputStream dest = new FileOutputStream(zipFileName);
//            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
//                    dest));
//            byte data[] = new byte[BUFFER];
//
//            for (int i = 0; i < _files.length; i++) {
//                LogUtil.debug("Compress, adding: " + _files[i]);
//                FileInputStream fi = new FileInputStream(_files[i]);
//                origin = new BufferedInputStream(fi, BUFFER);
//
//                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i]
//                        .lastIndexOf("/") + 1));
//                out.putNextEntry(entry);
//                int count;
//
//                while ((count = origin.read(data, 0, BUFFER)) != -1) {
//                    out.write(data, 0, count);
//                }
//                origin.close();
//            }
//            out.close();
//        } catch (Exception e) {
//            LogUtil.error("Unable zip the files: " + _files.toString() + " - " + e.getMessage());
//        }
//    }
//
//    private static void dirChecker(String dir) {
//        File f = new File(dir);
//        if (!f.isDirectory()) {
//            f.mkdirs();
//        }
//    }
//
//    public static void deleteRecursive(File path) {
//        if (path.isDirectory())
//            for (File child : path.listFiles())
//                deleteRecursive(child);
//
//        path.delete();
//    }
//
//    public static void startCameraIntent(Fragment pFragment, int requestCode, Context context) {
//        if (pFragment != null) {
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                Uri uriFromFile = FileProvider.getUriForFile(context,
//                        Constants.FILE_PROVIDER_AUTHORITIES, getOutputMediaFile(context));
//
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriFromFile);
//            } else {
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(getOutputMediaFile(context)));
//            }
//
//            pFragment.startActivityForResult(cameraIntent, requestCode);
//        }
//    }
//
//    public static File getOutputMediaFile(Context context) {
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
//
//        if (!mediaStorageDir.exists()) {
//            mediaStorageDir.mkdir();
//        }
//
//        File documentFile = null;
//        try {
//            documentFile = File.createTempFile(
//                    "IMG_TEMP",
//                    ".jpg",
//                    mediaStorageDir
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Prefs.putString(URI_CAPTURED_IMAGE, documentFile.getAbsolutePath());
//
////        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        return documentFile;
//    }
//
//    public static void pickPicture(Fragment pFragment, int requestCode) {
//        if (pFragment != null) {
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent.setType("image/*");
//
//            if (intent.resolveActivity(pFragment.getActivity().getPackageManager()) != null) {
//                pFragment.startActivityForResult(intent, requestCode);
//            } else {
//                Toast.makeText(pFragment.getContext(), pFragment.getString(R.string.text_no_apps_can_perform_this_action), Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
//
//    public static void pickFile(Fragment pFragment, int requestCode) {
//        if (pFragment != null) {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("file/*");
//            pFragment.startActivityForResult(intent, requestCode);
//        }
//
////        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////        intent.setType("file/*");
////        startActivityForResult(intent,PICKFILE_RESULT_CODE);
//    }
//
//    /**
//     * Check the device to make sure it has the Google Play Services APK. If
//     * it doesn't, display a dialog that allows users to download the APK from
//     * the Google Play Store or enable it in the device's system settings.
//     */
//    public static boolean checkGooglePlayServiceVersion(Activity pParentActivity) {
//        boolean ret = false;
//        if (pParentActivity != null && !pParentActivity.isFinishing()) {
//            GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//            int resultCode = apiAvailability.isGooglePlayServicesAvailable(pParentActivity);
//            if (resultCode != ConnectionResult.SUCCESS) {
//                if (apiAvailability.isUserResolvableError(resultCode)) {
//                    apiAvailability.getErrorDialog(pParentActivity, resultCode, Constants.PLAY_SERVICES_RESOLUTION_REQUEST)
//                            .show();
//                } else {
//                    LogUtil.info("This device is not supported.");
//                }
//            } else {
//                ret = true;
//            }
//        }
//        return ret;
//    }
//
//    public static boolean isGooglePlayServiceValid(Activity pParentActivity) {
//        boolean ret = true;
//        if (pParentActivity != null && !pParentActivity.isFinishing()) {
//            GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//            int resultCode = apiAvailability.isGooglePlayServicesAvailable(pParentActivity);
//            ret = resultCode == ConnectionResult.SUCCESS;
//        }
//        return ret;
//    }
//
//    public static boolean isGooglePlayServiceValid(Context context) {
//        boolean ret = true;
//        if (context != null) {
//            GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//            int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
//            ret = resultCode == ConnectionResult.SUCCESS;
//        }
//        return ret;
//    }
//
////    public static void closeSoftKeyboard(Context context, View view) {
////        if (view == null) {
////            return;
////        }
////        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
////    }
////
////    public static void openSoftKeyboard(Context context) {
////        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
////    }
//
//    public static void closeSoftKeyboard(Context context) {
//        try {
//            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//            if ((((Activity) context).getCurrentFocus() != null) && (((Activity) context).getCurrentFocus().getWindowToken() != null)) {
//                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void openSoftKeyboard(Context context) {
//        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//    }
//
//    @NonNull
//    public static RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(
//                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
//    }
//
//    @NonNull
//    public static MultipartBody.Part prepareFilePart(String partName, File file) {
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
//    }
//
//    public static boolean isEnableAnimatorScale(Context context) {
//        boolean isAnimationSupport = false;
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                isAnimationSupport = Settings.Global.getFloat(context.getContentResolver(), Settings.Global.ANIMATOR_DURATION_SCALE) != 0;
//            } else {
//                isAnimationSupport = Settings.System.getFloat(context.getContentResolver(), Settings.System.ANIMATOR_DURATION_SCALE) != 0;
//            }
//        } catch (Settings.SettingNotFoundException e) {
//            e.printStackTrace();
//            return true;
//        }
//        return isAnimationSupport;
//    }
//
//    public static void initIdentity(Context context) {
//        HeaderDeviceIdentity headerDeviceIdentity = new HeaderDeviceIdentity();
//        headerDeviceIdentity.setDeviceManufacturer(DeviceIdentityUtil.getDeviceManufacturer());
//        headerDeviceIdentity.setDeviceModel(DeviceIdentityUtil.getDeviceModel());
//        headerDeviceIdentity.setDeviceResolution(DeviceIdentityUtil.getDeviceResolution(context));
//        headerDeviceIdentity.setDeviceIdentifier(DeviceIdentityUtil.getImei(context));
//        headerDeviceIdentity.setDeviceOs(DeviceIdentityUtil.getDeviceOs());
//        headerDeviceIdentity.setDeviceOsVersion(DeviceIdentityUtil.getDeviceOsVersion());
//        headerDeviceIdentity.setDeviceFcmId(DeviceIdentityUtil.getDeviceFcmId(context));
//        headerDeviceIdentity.setDeviceAndroidId(DeviceIdentityUtil.getAndroidId(context));
//        headerDeviceIdentity.setDeviceAdsClientId(DeviceIdentityUtil.getDeviceAdvertisingClientId());
//        headerDeviceIdentity.setDeviceId1(DeviceIdentityUtil.getDeviceId1(context));
//        headerDeviceIdentity.setDeviceId2(DeviceIdentityUtil.getDeviceId2(context));
//        headerDeviceIdentity.setDeviceId3(DeviceIdentityUtil.getDeviceId3(context));
//        headerDeviceIdentity.setAppVersion(DeviceIdentityUtil.getAppVersion(context));
//        headerDeviceIdentity.setConfigVersion(DeviceIdentityUtil.getDeviceConfigVersion());
//        headerDeviceIdentity.setIkandado(getEncryptedPinLock());
//
//        Gson gson = new Gson();
//        String json = gson.toJson(headerDeviceIdentity);
//        String encrypted = Base64.encodeToString(json.getBytes(), Base64.NO_WRAP);
//        Prefs.putString(HeaderDeviceIdentity.TAG, encrypted);
//
//        DeviceIdentityUtil.setAdvertisingIdClient(context);
//    }
//
//    public static void initIdentityPIN(Context context, String pin) {
//        DeviceIdentityUtil.setAdvertisingIdClient(context);
//
//        String param = "@" + pin + "%";
//        String encryptedPin = Base64.encodeToString(param.getBytes(), Base64.NO_WRAP);
//
//        HeaderDeviceIdentity headerDeviceIdentity = new HeaderDeviceIdentity();
//        headerDeviceIdentity.setDeviceManufacturer(DeviceIdentityUtil.getDeviceManufacturer());
//        headerDeviceIdentity.setDeviceModel(DeviceIdentityUtil.getDeviceModel());
//        headerDeviceIdentity.setDeviceResolution(DeviceIdentityUtil.getDeviceResolution(context));
//        headerDeviceIdentity.setDeviceIdentifier(DeviceIdentityUtil.getImei(context));
//        headerDeviceIdentity.setDeviceOs(DeviceIdentityUtil.getDeviceOs());
//        headerDeviceIdentity.setDeviceOsVersion(DeviceIdentityUtil.getDeviceOsVersion());
//        headerDeviceIdentity.setDeviceFcmId(DeviceIdentityUtil.getDeviceFcmId(context));
//        headerDeviceIdentity.setDeviceAndroidId(DeviceIdentityUtil.getAndroidId(context));
//        headerDeviceIdentity.setDeviceAdsClientId(DeviceIdentityUtil.getDeviceAdvertisingClientId());
//        headerDeviceIdentity.setDeviceId1(DeviceIdentityUtil.getDeviceId1(context));
//        headerDeviceIdentity.setDeviceId2(DeviceIdentityUtil.getDeviceId2(context));
//        headerDeviceIdentity.setDeviceId3(DeviceIdentityUtil.getDeviceId3(context));
//        headerDeviceIdentity.setAppVersion(DeviceIdentityUtil.getAppVersion(context));
//        headerDeviceIdentity.setConfigVersion(DeviceIdentityUtil.getDeviceConfigVersion());
//        headerDeviceIdentity.setIkandado(encryptedPin);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(headerDeviceIdentity);
//        String encrypted = Base64.encodeToString(json.getBytes(), Base64.NO_WRAP);
//        Prefs.putString(HeaderDeviceIdentity.TAG, encrypted);
//    }
//
//    /**
//     * Method to get address from latitude and longitude.
//     *
//     * @param m_context
//     * @param LATITUDE
//     * @param LONGITUDE
//     * @return
//     */
//    public static String getCompleteAddressString(Context m_context, double LATITUDE, double LONGITUDE) {
//        String strAdd = "";
//        Geocoder geocoder = new Geocoder(m_context, Locale.getDefault());
//        try {
//            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
//            if (addresses != null) {
//                Address returnedAddress = addresses.get(0);
//
//                Gson gson = new Gson();
//                String jsonAddress = gson.toJson(returnedAddress);
//
////                StringBuilder strReturnedAddress = new StringBuilder("");
////
////                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
////                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(":");
////                }
//                strAdd = jsonAddress;
//                LogUtil.debug("My Current location add:\n " + jsonAddress);
//            }
////            else {
////                Log.v("My Current location address", "No Address returned!");
////            }
//        } catch (Exception e) {
//            LogUtil.error("Failed get address: " + e.getMessage());
////            Toast.makeText(m_context, "Sorry, Your location cannot be retrieved !" + e.getMessage(), Toast.LENGTH_SHORT).show();
////            Log.v("My Current location address", "Cannot get Address!");
//        }
//        return strAdd;
//    }
//
//    public static boolean isAppIsInBackground(Context context) {
//        boolean isInBackground = true;
//
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
//            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
//
//            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
//
//                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//
//                    for (String activeProcess : processInfo.pkgList) {
//
//                        if (activeProcess.equals(context.getPackageName())) {
//                            isInBackground = false;
//                        }
//                    }
//                }
//            }
//        } else {
//            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
//            ComponentName componentInfo = taskInfo.get(0).topActivity;
//
//            if (componentInfo.getPackageName().equals(context.getPackageName())) {
//                isInBackground = false;
//            }
//        }
//
//        return isInBackground;
//    }
//
//    public static boolean isAppInMainActivity() {
//        boolean isMainActivity = false;
//
//        String activeActivity = Prefs.getString("ACTIVE_ACTIVITY", null);
//        if (activeActivity != null && activeActivity.equals("MainActivity")) {
//            isMainActivity = true;
//        }
//
//        return isMainActivity;
//    }
//
//    public static void loadImageToImageView(FragmentActivity activity, String filePath, ImageView view) {
//        if (isFileExist(filePath)) {
//            File file = new File(Environment.getExternalStorageDirectory(), filePath);
//            Glide.with(activity).load(file).dontAnimate().dontTransform().into(view);
//        }
//    }
//
//    public static boolean isFileExist(String filePath) {
////        String pathPictures = Environment.DIRECTORY_PICTURES + "/TOPJEK/" + fileName;
//        File file = new File(Environment.getExternalStorageDirectory(), filePath);
//
//        if (file.exists()) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static String getPinLock() {
//        String encrypted = Prefs.getString(Constants.PREFS_KEY_PIN, "");
//        if (encrypted.isEmpty()) {
//            return "";
//        } else {
//            String decrypted = "";
//            try {
//                decrypted = new String(Base64.decode(encrypted, Base64.NO_WRAP), "UTF-8");
//                decrypted = decrypted.substring(1, 5);
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            return decrypted;
//        }
//    }
//
//    public static void setPinLock(String pin) {
//        String param = "@" + pin + "%";
//        String encrypted = Base64.encodeToString(param.getBytes(), Base64.NO_WRAP);
//
//        Prefs.putString(Constants.PREFS_KEY_PIN, encrypted);
//    }
//
//    public static String getEncryptedPinLock() {
//        return Prefs.getString(Constants.PREFS_KEY_PIN, "");
//    }
//
//    public static String downloadFile(DownloadManager downloadManager,
//                                      Context context,
//                                      Uri uri,
//                                      String fileName,
//                                      String requestTitle,
//                                      String requestDesc) {
//
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//
//        String documentDirectory = null;
//        if (Build.VERSION.SDK_INT >= 19) {
//            documentDirectory = Environment.DIRECTORY_DOCUMENTS;
//        } else {
//            documentDirectory = "Documents";
//        }
//
//        request.setTitle(requestTitle);
//        request.setDescription(requestDesc);
//        request.setDestinationInExternalPublicDir(documentDirectory, fileName);
//
//        File file = new File(Environment.getExternalStoragePublicDirectory(documentDirectory) + File.separator + fileName);
//        if (file.exists()) {
//            file.delete();
//        }
//
//        downloadManager.enqueue(request);
//
//        return file.getAbsolutePath();
//    }
//
//    public static boolean isExpressLoan() {
//        return (StaticConfig.getExpressLoan() == 0) ? false : true ;
//    }
//
//    public static String getSha1Hex(String text) {
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
//            messageDigest.update(text.getBytes("UTF-8"));
//            byte[] bytes = messageDigest.digest();
//            StringBuilder buffer = new StringBuilder();
//            for (byte b : bytes) {
//                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
//            }
//            return buffer.toString();
//        } catch (Exception ignored) {
//            ignored.printStackTrace();
//            return null;
//        }
//    }
//
//    public static AmplitudeClient getAmplitude(Context context, Application application) {
//        AmplitudeClient amplitude = Amplitude.getInstance().initialize(context, BuildConfig.AMPLITUDE_API_KEY).enableForegroundTracking(application);
//
//        if (amplitude == null) {
//            amplitude = Amplitude.getInstance().initialize(context, BuildConfig.AMPLITUDE_API_KEY).enableForegroundTracking(application);
//        }
//        return amplitude;
//    }
//
//    public static void setFirebaseAnalyticsEvent(Context context, String event, JSONObject jsonObject) {
//        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
//
//        Bundle params = new Bundle();
//
//        if (null != jsonObject) {
//            Map<String, String> map = new Gson().fromJson(jsonObject.toString(), Map.class);
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                params.putString(entry.getKey(), entry.getValue());
//                LogUtil.debug(entry.getValue());
//            }
//        }
//
//        firebaseAnalytics.logEvent(event, params);
//    }
//
//    public static void setTrackingEvent(Context context, Application application, String event, JSONObject jsonObject) {
//        if (null != jsonObject) {
////            getMixpanelAPI().track(event, jsonObject);
//            getAmplitude(context, application).logEvent(event, jsonObject);
//            setFirebaseAnalyticsEvent(context, event, jsonObject);
//
//            java.lang.reflect.Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
//            Gson gson = new Gson();
//            Map<String, Object> map = gson.fromJson(jsonObject.toString(), mapType);
//            if (isAppsFlyerSupported(event)) {
//                AppsFlyerLib.getInstance().trackEvent(context, event, map);
////                LogUtil.error("send tracking appsflyer: " + event);
//            }
//        } else {
////            getMixpanelAPI().track(event);
//            getAmplitude(context, application).logEvent(event);
//            setFirebaseAnalyticsEvent(context, event, null);
//            if (isAppsFlyerSupported(event)) {
//                AppsFlyerLib.getInstance().trackEvent(context, event, null);
////                LogUtil.error("send tracking appsflyer: " + event);
//            }
//
//        }
//    }
//
//    public static boolean isAppsFlyerSupported(String event) {
//        String[] listEvent = {TrackingEvents.Home_screen_viewed, TrackingEvents.Home_buttonAjukanPinjaman_clicked,
//                TrackingEvents.Calculator_buttonPinjamSekarang_clicked, TrackingEvents.DokumenPersetujuan_buttonSetuju_clicked};
//
//        for (String sEvent:listEvent) {
//            if (event.equalsIgnoreCase(sEvent)) {
////                LogUtil.error("track appsflyer dude: " + event);
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public static boolean isBrokenSamsungDevice() {
//        return (Build.MANUFACTURER.equalsIgnoreCase("samsung")
//                && isBetweenAndroidVersions(
//                Build.VERSION_CODES.LOLLIPOP,
//                Build.VERSION_CODES.LOLLIPOP_MR1));
//    }
//
//    public static boolean isBetweenAndroidVersions(int min, int max) {
//        return Build.VERSION.SDK_INT >= min && Build.VERSION.SDK_INT <= max;
//    }
//
//    public static String readJsonAssets(Context context, String jsonFileName) throws IOException {
//        InputStream is = context.getAssets().open("json/" + jsonFileName);
//        int size = is.available();
//        byte[] buffer = new byte[size];
//        is.read(buffer);
//        is.close();
//
//        String jsonContent = new String(buffer, "UTF-8");
//
//        return jsonContent;
//    }
//
//    public static void createAlarmManager(Context context) {
//        // checking running services
//        if (!isMyServiceRunning(context, PowerStatusService.class)) {
//            Intent intentPower = new Intent(context, PowerStatusService.class);
//            context.startService(intentPower);
//        }
//        if (!isMyServiceRunning(context, ConnectionStatusService.class)) {
//            Intent intentCnn = new Intent(context, ConnectionStatusService.class);
//            context.startService(intentCnn);
//        }
//
//        AlarmManager alarmMgr;
//        AlarmManager alarmMgr2;
//        PendingIntent alarmIntent;
//        PendingIntent alarmIntent2;
//
//        // battery level
//        boolean alarmUp = (PendingIntent.getBroadcast(context, 0,
//                new Intent(context, BatteryLevelReceiver.class),
//                PendingIntent.FLAG_NO_CREATE) != null);
//        if (!alarmUp) {
//            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(context, BatteryLevelReceiver.class);
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//            // Set the alarm to start at 08.00 a.m.
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(System.currentTimeMillis());
//            calendar.set(Calendar.HOUR_OF_DAY, 10);
//            calendar.set(Calendar.MINUTE, 50);
//            // setRepeating() every 7 hours
//            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
////                1000 * 60 * 60 * 6, alarmIntent);
//                    1000 * 60 * 3, alarmIntent);
//        }
//
//        // data usage
//        boolean alarmUp2 = (PendingIntent.getBroadcast(context, 1,
//                new Intent(context, DataUsageReceiver.class),
//                PendingIntent.FLAG_NO_CREATE) != null);
//        if (!alarmUp2) {
//            alarmMgr2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            Intent inn = new Intent(context, DataUsageReceiver.class);
//            alarmIntent2 = PendingIntent.getBroadcast(context, 1, inn, PendingIntent.FLAG_CANCEL_CURRENT);
//            // Set the alarm to start at 23.00 a.m.
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(System.currentTimeMillis());
//            cal.set(Calendar.HOUR_OF_DAY, 10);
//            cal.set(Calendar.MINUTE, 50);
//            // setRepeating() every 24 hours
//            alarmMgr2.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
////                1000 * 60 * 60 * 24, alarmIntent2);
//                    1000 * 60 * 2, alarmIntent2);
//        }
//
//        // getSMS
//        GetMessageInfo messageInfo = new GetMessageInfo(context);
//        messageInfo.checkSMS();
//    }
//
//    private static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                Log.i("isMyServiceRunning?", true + "");
//                return true;
//            }
//        }
//        Log.i("isMyServiceRunning?", false + "");
//        return false;
//    }
//
//}
