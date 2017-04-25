package com.songoda.arconix.packets;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.packets.ActionBar.ActionBar;
import com.songoda.arconix.packets.Hologram.Hologram;
import com.songoda.arconix.packets.Hologram.Hologram1_11R1;
import com.songoda.arconix.packets.Particle.Particle;
import com.songoda.arconix.packets.Ping.UserPing;
import com.songoda.arconix.packets.SignEditor.SignEditor;
import com.songoda.arconix.packets.TabList.TabList;
import com.songoda.arconix.packets.Title.Title;

/**
 * Created by songoda on 3/16/2017.
 */
public class PacketLibrary {

    Arconix plugin = Arconix.pl();


    private Particle particleManager;
    private ActionBar actionBarManager;
    private Hologram hologramManager;
    private Title titleManager;
    private SignEditor signEditorManager;
    private TabList tabListManager;
    private UserPing pingManager;

    public void setupPackets() {
        switch (plugin.serverVersion) {
            case "v1_11_R1":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_11R1();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_11R1();
                this.hologramManager = new Hologram1_11R1();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_11R1();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_11R1();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_11R1();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_11R1();
                break;
            case "v1_10_R1":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_10R1();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_10R1();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_10R1();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_10R1();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_10R1();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_10R1();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_10R1();
                break;
            case "v1_9_R2":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_9R2();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_9R2();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_9R2();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_9R2();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_9R2();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_9R2();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_9R2();
                break;
            case "v1_9_R1":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_9R1();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_9R1();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_9R1();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_9R1();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_9R1();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_9R1();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_9R1();
                break;
            case "v1_8_R3":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_8R3();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_8R3();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_8R3();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_8R3();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_8R3();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_8R3();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_8R3();
                break;
            case "v1_8_R2":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_8R2();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_8R2();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_8R2();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_8R2();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_8R2();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_8R2();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_8R2();
                break;
            case "v1_8_R1":
                this.particleManager = new com.songoda.arconix.packets.Particle.Particle1_8R1();
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_8R1();
                this.hologramManager = new com.songoda.arconix.packets.Hologram.Hologram1_8R1();
                this.titleManager = new com.songoda.arconix.packets.Title.Title1_8R1();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_8R1();
                this.tabListManager = new com.songoda.arconix.packets.TabList.TabList1_8R1();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_8R1();
                break;
            case "v1_7_R4":
                this.actionBarManager = new com.songoda.arconix.packets.ActionBar.ActionBar1_7R4();
                this.signEditorManager = new com.songoda.arconix.packets.SignEditor.SignEditor1_7R4();
                this.pingManager = new com.songoda.arconix.packets.Ping.Ping1_7R4();
                break;
            default:
                break;
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
