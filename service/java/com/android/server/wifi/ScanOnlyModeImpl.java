/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.android.server.wifi;

import android.net.DhcpResultsParcelable;
import android.net.Network;
import android.net.wifi.IWifiConnectedNetworkScorer;
import android.net.wifi.WifiAnnotations;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiDppConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.IProvisioningCallback;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.nl80211.DeviceWiphyCapabilities;
import android.os.IBinder;
import android.os.Message;
import android.os.WorkSource;

import com.android.server.wifi.WifiNative.RxFateReport;
import com.android.server.wifi.WifiNative.TxFateReport;
import com.android.server.wifi.util.ActionListenerWrapper;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Used to respond to calls to ClientMode interface when ClientModeImpl is not up
 * i.e. in scan only mode.
 *
 * Note: this class is currently a singleton as it has no state.
 */
public class ScanOnlyModeImpl implements ClientMode {

    private static final long ID = -2;

    @Override
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) { }

    @Override
    public void connectNetwork(NetworkUpdateResult result, ActionListenerWrapper wrapper,
            int callingUid) {
        // wifi off, can't connect.
        wrapper.sendFailure(WifiManager.BUSY);
    }

    @Override
    public void saveNetwork(NetworkUpdateResult result, ActionListenerWrapper wrapper,
            int callingUid) {
        // wifi off, nothing more to do here.
        wrapper.sendSuccess();
    }

    @Override
    public void disconnect() { }

    @Override
    public void reconnect(WorkSource ws) { }

    @Override
    public void reassociate() { }

    @Override
    public void startConnectToNetwork(int networkId, int uid, String bssid) { }

    @Override
    public void startRoamToNetwork(int networkId, String bssid) { }

    @Override
    public boolean setWifiConnectedNetworkScorer(
            IBinder binder, IWifiConnectedNetworkScorer scorer) {
        // don't fail the public API when wifi is off.
        return true;
    }

    @Override
    public void clearWifiConnectedNetworkScorer() { }

    @Override
    public void resetSimAuthNetworks(@ClientModeImpl.ResetSimReason int resetReason) { }

    @Override
    public void onBluetoothConnectionStateChanged() { }

    @Override
    public WifiInfo syncRequestConnectionInfo() {
        return new WifiInfo();
    }

    @Override
    public boolean syncQueryPasspointIcon(long bssid, String fileName) {
        return false;
    }

    @Override
    public Network syncGetCurrentNetwork() {
        return null;
    }

    @Override
    public DhcpResultsParcelable syncGetDhcpResultsParcelable() {
        return new DhcpResultsParcelable();
    }

    @Override
    public long getSupportedFeatures() {
        return 0L;
    }

    @Override
    public boolean syncStartSubscriptionProvisioning(int callingUid, OsuProvider provider,
            IProvisioningCallback callback) {
        return false;
    }

    @Override
    public boolean isWifiStandardSupported(@WifiAnnotations.WifiStandard int standard) {
        return false;
    }

    @Override
    public void enableTdls(String remoteMacAddress, boolean enable) { }

    @Override
    public void dumpIpClient(FileDescriptor fd, PrintWriter pw, String[] args) { }

    @Override
    public void dumpWifiScoreReport(FileDescriptor fd, PrintWriter pw, String[] args) { }

    @Override
    public void enableVerboseLogging(boolean verbose) { }

    @Override
    public String getFactoryMacAddress() {
        return null;
    }

    @Override
    public WifiConfiguration getConnectedWifiConfiguration() {
        return null;
    }

    @Override
    public WifiConfiguration getConnectingWifiConfiguration() {
        return null;
    }

    @Override
    public String getConnectedBssid() {
        return null;
    }

    @Override
    public String getConnectingBssid() {
        return null;
    }

    @Override
    public WifiLinkLayerStats getWifiLinkLayerStats() {
        return null;
    }

    @Override
    public boolean setPowerSave(boolean ps) {
        return false;
    }

    @Override
    public boolean setLowLatencyMode(boolean enabled) {
        return false;
    }

    @Override
    public WifiMulticastLockManager.FilterController getMcastLockManagerFilterController() {
        return new WifiMulticastLockManager.FilterController() {
            @Override
            public void startFilteringMulticastPackets() { }
            @Override
            public void stopFilteringMulticastPackets() { }
        };
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isConnecting() {
        return false;
    }

    @Override
    public boolean isRoaming() {
        return false;
    }

    @Override
    public boolean isDisconnected() {
        return true;
    }

    @Override
    public boolean isSupplicantTransientState() {
        return false;
    }

    @Override
    public void probeLink(LinkProbeCallback callback, int mcs) {
        callback.onFailure(LinkProbeCallback.LINK_PROBE_ERROR_NOT_CONNECTED);
    }
    @Override
    public void setTrafficPoller(WifiTrafficPoller trafficPoller) {}

    @Override
    public void sendMessageToClientModeImpl(Message msg) { }

    @Override
    public long getId() {
        return ID;
    }

    @Override
    public void setMboCellularDataStatus(boolean available) { }

    @Override
    public WifiNative.RoamingCapabilities getRoamingCapabilities() {
        return null;
    }

    @Override
    public boolean configureRoaming(WifiNative.RoamingConfig config) {
        return false;
    }

    @Override
    public boolean setCountryCode(String countryCode) {
        return false;
    }

    @Override
    public List<TxFateReport> getTxPktFates() {
        return new ArrayList<>();
    }

    @Override
    public List<RxFateReport> getRxPktFates() {
        return new ArrayList<>();
    }

    @Override
    public DeviceWiphyCapabilities getDeviceWiphyCapabilities() {
        return null;
    }

    @Override
    public boolean requestAnqp(String bssid, Set<Integer> anqpIds, Set<Integer> hs20Subtypes) {
        return false;
    }

    @Override
    public boolean requestVenueUrlAnqp(String bssid) {
        return false;
    }

    @Override
    public boolean requestIcon(String bssid, String fileName) {
        return false;
    }

    @Override
    public int syncDppAddBootstrapQrCode(String uri) {
        return -1;
    }
    @Override
    public int syncDppBootstrapGenerate(WifiDppConfig config) {
        return -1;
    }
    @Override
    public String syncDppGetUri(int bootstrap_id) {
        return null;
    }
    @Override
    public int syncDppBootstrapRemove(int bootstrap_id) {
        return -1;
    }
    @Override
    public int syncDppListen(String frequency, int dpp_role, boolean qr_mutual, boolean netrole_ap) {
      return -1;
    }

    @Override
    public void dppStopListen() {}
    @Override
    public int syncDppConfiguratorAdd(String curve, String key, int expiry) {
        return -1;
    }
    @Override
    public int syncDppConfiguratorRemove(int config_id) {
        return -1;
    }
    @Override
    public int syncDppStartAuth(WifiDppConfig config) {
        return -1;
    }
    @Override
    public String syncDppConfiguratorGetKey(int id) {
        return null;
    }
    @Override
    public String doDriverCmd(String command) {
        return null;
    }
}
