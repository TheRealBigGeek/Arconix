package com.songoda.arconix.api.packets;

import org.bukkit.Bukkit;

/**
 * Created by songoda on 3/16/2017.
 */
public class PacketLibrary {

    private Particle particleManager;
    private ActionBar actionBarManager;
    private Hologram hologramManager;
    private Enchantment enchantmentManager;
    private Title titleManager;
    private SignEditor signEditorManager;
    private TabList tabListManager;
    private UserPing pingManager;

    public void setupPackets(String serverVersion) {
        try {
            switch (serverVersion) {
                case "v1_13_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_13_R1.Particle1_13R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_13_R1.ActionBar1_13R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_13_R1.Hologram1_13R1").getConstructor().newInstance();
                    enchantmentManager = (Enchantment) Class.forName("com.songoda.arconix.nms.v1_13_R1.Enchantment1_13R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_13_R1.Title1_13R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_13_R1.SignEditor1_13R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_13_R1.TabList1_13R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_13_R1.Ping1_13R1").getConstructor().newInstance();
                    break;
                case "v1_12_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_12_R1.Particle1_12R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_12_R1.ActionBar1_12R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_12_R1.Hologram1_12R1").getConstructor().newInstance();
                    enchantmentManager = (Enchantment) Class.forName("com.songoda.arconix.nms.v1_12_R1.Enchantment1_12R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_12_R1.Title1_12R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_12_R1.SignEditor1_12R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_12_R1.TabList1_12R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_12_R1.Ping1_12R1").getConstructor().newInstance();
                    break;
                case "v1_11_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_11_R1.Particle1_11R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_11_R1.ActionBar1_11R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_11_R1.Hologram1_11R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_11_R1.Title1_11R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_11_R1.SignEditor1_11R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_11_R1.TabList1_11R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_11_R1.Ping1_11R1").getConstructor().newInstance();
                    break;
                case "v1_10_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_10_R1.Particle1_10R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_10_R1.ActionBar1_10R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_10_R1.Hologram1_10R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_10_R1.Title1_10R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_10_R1.SignEditor1_10R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_10_R1.TabList1_10R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_10_R1.UserPing1_10R1").getConstructor().newInstance();
                    break;
                case "v1_9_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_9_R1.Particle1_9R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_9_R1.ActionBar1_9R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_9_R1.Hologram1_9R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_9_R1.Title1_9R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_9R1.SignEditor1_9R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_9_R1.TabList1_9R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_9_R1.UserPing1_9R1").getConstructor().newInstance();
                    break;
                case "v1_8_R3":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_8_R3.Particle1_8R3").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_8_R3.ActionBar1_8R3").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_8_R3.Hologram1_8R3").getConstructor().newInstance();
                    enchantmentManager = (Enchantment) Class.forName("com.songoda.arconix.nms.v1_8_R3.Enchantment1_8R3").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_8_R3.Title1_8R3").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_8_R3.SignEditor1_8R3").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_8_R3.TabList1_8R3").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_8_R3.Ping1_8R3").getConstructor().newInstance();
                    break;
                case "v1_8_R2":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_8_R2.Particle1_8R2").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_8_R2.ActionBar1_8R2").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_8_R2.Hologram1_8R2").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_8_R2.Title1_8R2").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_8_R2.SignEditor1_8R2").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_8_R2.TabList1_8R2").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_8_R2.Ping1_8R2").getConstructor().newInstance();
                    break;
                case "v1_8_R1":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_8_R1.Particle1_8R1").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_8_R1.ActionBar1_8R1").getConstructor().newInstance();
                    hologramManager = (Hologram) Class.forName("com.songoda.arconix.nms.v1_8_R1.Hologram1_8R1").getConstructor().newInstance();
                    titleManager = (Title) Class.forName("com.songoda.arconix.nms.v1_8_R1.Title1_8R1").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_8_R1.SignEditor1_8R1").getConstructor().newInstance();
                    tabListManager = (TabList) Class.forName("com.songoda.arconix.nms.v1_8_R1.TabList1_8R1").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_8_R1.Ping1_8R1").getConstructor().newInstance();
                    break;
                case "v1_7_R4":
                    particleManager = (Particle) Class.forName("com.songoda.arconix.nms.v1_7_R4.Particle1_7R4").getConstructor().newInstance();
                    actionBarManager = (ActionBar) Class.forName("com.songoda.arconix.nms.v1_7_R4.ActionBar1_7R4").getConstructor().newInstance();
                    signEditorManager = (SignEditor) Class.forName("com.songoda.arconix.nms.v1_7_R4.SignEditor1_7R4").getConstructor().newInstance();
                    pingManager = (UserPing) Class.forName("com.songoda.arconix.nms.v1_7_R4.Ping1_7R4").getConstructor().newInstance();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Could not find support for this CraftBukkit version.");
            Bukkit.getLogger().info("Check for updates please!");
        }
    }

    public Particle getParticleManager() {
        return particleManager;
    }

    public ActionBar getActionBarManager() {
        return actionBarManager;
    }

    public Hologram getHologramManager() {
        return hologramManager;
    }

    public Title getTitleManager() {
        return titleManager;
    }

    public Enchantment getEnchantmentManager() {
        return enchantmentManager;
    }

    public SignEditor getSignEditorManager() {
        return signEditorManager;
    }

    public TabList getTabListManager() {
        return tabListManager;
    }

    public UserPing getPingManager() {
        return pingManager;
    }
}