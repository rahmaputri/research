package com.rp.rahmawatiputrianasari.research00.presenter;

import android.app.Activity;

import com.rp.rahmawatiputrianasari.research00.view.SplashScreenView;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */

public class SplashPresenter {
    private Activity context;
    private SplashScreenView mView;
//        private RestClient restClient;
//        private DatabaseHelper mDatabaseHelper;
//        private Dao<Case, Integer> caseDao;
//        private Dao<IDNA.Info, Integer> infoDao;

    private boolean isDialogUpdateShown;

    public SplashPresenter(Activity context, SplashScreenView mView) {
        this.context = context;
        this.mView = mView;
//            restClient = new RestClient();
//
//            mDatabaseHelper = DatabaseHelper.getInstance(context);
//
//            caseDao = mDatabaseHelper.getCaseDao();
//            infoDao = mDatabaseHelper.getInfoDao();
    }

    public void releaseView() {
        this.mView = null;
    }

    public void doProvisioning() {
        if (mView == null)
            return;

        mView.goToNextScreen();
    }
//
//            restClient.getApiService().doProvisioning().enqueue(new Callback<BaseResponse<ProvisioningResponse>>() {
//                @Override
//                public void onResponse(Call<BaseResponse<ProvisioningResponse>> call, Response<BaseResponse<ProvisioningResponse>> response) {
//
////                if config_version > current app installed then udate hit api config.
////                if app_version > current app installed then do force update (1) or soft update (0) based on param "is_force_update" is true/1 or false/0
//
//                    if (mView == null)
//                        return;
//
//                    if (response.isSuccessful()) {
//
//                        ProvisioningResponse provisioningResponse = response.body().getContent();
//////                    // FIXME: 4/26/17 update line code below after api config ready
////                    provisioningResponse.setAppVersion("1.1");
////                    provisioningResponse.setConfigVersion("1.0");
////                    provisioningResponse.setIsForceUpdate(0);
//
////                    Utils.showToast(context, "provisioning: " + provisioningResponse.toString());
//
//                        // if app_version != current app installed then do force update (1) or soft update (0) based on param "is_force_update" is true/1 or false/0
//                        if (!provisioningResponse.getAppVersion().equalsIgnoreCase(BuildConfig.VERSION_NAME)) {
//                            isDialogUpdateShown = true;
//
//                            if (provisioningResponse.isForceUpdate()) {
//                                // do force update
//                                mView.showDialog(context.getString(R.string.text_uangteman), context.getString(R.string.text_update_version), Constants.ACTION_FORCE_UPDATE);
//                            } else {
//                                // do soft update
//                                mView.showDialog(context.getString(R.string.text_uangteman), context.getString(R.string.text_update_version), Constants.ACTION_SOFT_UPDATE);
//                            }
//                        }
//
//                        // if config_version != current app installed then update hit api config.
//                        if (!provisioningResponse.getConfigVersion().equalsIgnoreCase(Prefs.getString(Constants.PREFS_CONFIG_VERSION, ""))) {
//                            updateConfig(provisioningResponse.getConfigVersion());
//                        } else {
//                            if (!isDialogUpdateShown)
//                                mView.goToNextScreen();
//                        }
//                        // FIXME: til here
//
//                    } else {
//                        // something wrong with server
////                    Utils.showToast(context, "provisioning: " + response.code());
//                        mView.showDialog(context.getString(R.string.text_uangteman), String.valueOf(response.code() + "\n" + response.message()), Constants.ACTION_NONE);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BaseResponse<ProvisioningResponse>> call, Throwable t) {
////                mView.showDialog("UangTeman", t.getMessage(), Constants.ACTION_NONE);
////                Utils.showToast(context, "provisioning: " + t.getMessage());
//                    mView.loadContentError();
//                }
//            });
//        }
//
//        public void updateConfig(final String configVersion) {
//            LogUtil.debug("get json file then save to db...");
//
//            restClient.getApiService().getConfig().enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                    if (mView == null)
//                        return;
//
//                    if (response.isSuccessful()) {
//                        LogUtil.debug("#parse the json file then save to db...");
//
//                        String fileNameZip = "config.zip";
//                        String dirNameExtract = "/colap";
//                        String fileName = "/config.json";
//
//                        try {
//                            int count;
//
//                            InputStream input = new ByteArrayInputStream(response.body().bytes());
//                            File path = Environment.getExternalStorageDirectory();
//                            File file = new File(path, fileNameZip);
//
//                            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
//                            byte data[] = new byte[1024];
//
//                            long total = 0;
//
//                            while ((count = input.read(data)) != -1) {
//                                total += count;
//                                output.write(data, 0, count);
//                            }
//
//                            output.flush();
//
//                            output.close();
//                            input.close();
//
//                            Utils.unzip(file, path.getAbsolutePath().concat(dirNameExtract));
//
//                            // load config.json into local db
//                            saveJsonToLocalDB(path.getAbsolutePath().concat(dirNameExtract.concat(fileName)), configVersion);
//                        } catch (IOException e) {
//                            LogUtil.error("Unable process file downloaded: " + e.getMessage());
//
//                            if (mView == null)
//                                return;
//
//                            if (!isDialogUpdateShown)
//                                mView.loadContentError();
//                        }
//                    } else {
//                        if (mView == null)
//                            return;
//
//                        if (!isDialogUpdateShown)
//                            mView.loadContentError();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    if (mView == null)
//                        return;
//
//                    if (!isDialogUpdateShown)
//                        mView.loadContentError();
//                }
//            });
//        }
//
//        private void saveJsonToLocalDB(String jsonPath, String configVersion) {
//
//            try {
//                Gson gson = new Gson();
//                final ConfigModel configModel = gson.fromJson(new FileReader(jsonPath), ConfigModel.class);
//
//                StaticConfig.setIntervalLocationFast(configModel.getInterval_location_fast());
//                StaticConfig.setIntervalLocationNormal(configModel.getInterval_location_normal());
//
//                TableUtils.clearTable(mDatabaseHelper.getConnectionSource(), Case.class);
//                TableUtils.clearTable(mDatabaseHelper.getConnectionSource(), Info.class);
//
//                caseDao.callBatchTasks(new Callable<Void>() {
//                    public Void call() throws SQLException {
//                        caseDao.create(configModel.getCases());
//                        return null;
//                    }
//                });
//
//                infoDao.callBatchTasks(new Callable<Void>() {
//                    public Void call() throws SQLException {
//                        infoDao.create(configModel.getInfos());
//                        return null;
//                    }
//                });
//
//                Prefs.putString(Constants.PREFS_CONFIG_VERSION, configVersion);
////            Utils.showToast(context, "success load and store config " + configVersion + " to DB");
//
//                if (!isDialogUpdateShown) {
//                    mView.goToNextScreen();
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
}
