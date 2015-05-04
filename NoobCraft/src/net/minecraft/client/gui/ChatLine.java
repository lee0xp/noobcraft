package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.util.IChatComponent;
import de.lee0xp.client.gui.Alert;

public class ChatLine
{
    /** GUI Update Counter value this Line was created at */
    private final int updateCounterCreated;
    private final IChatComponent lineString;

    /**
     * int value to refer to existing Chat Lines, can be 0 which means unreferrable
     */
    private final int chatLineID;
    private static final String __OBFID = "CL_00000627";

    public ChatLine(int p_i45000_1_, IChatComponent p_i45000_2_, int p_i45000_3_)
    {
        this.lineString = p_i45000_2_; 
        this.updateCounterCreated = p_i45000_1_;
        this.chatLineID = p_i45000_3_;
       // System.out.println(lineString.getFormattedText());
        if (lineString.getFormattedText().contains("§a §b §c §d §e §f §0 §1 §2 §3 §4 §5 §6 §7 §8 §9")){
        	Minecraft.getMinecraft().getNetHandler().addToSendQueue(new S00PacketDisconnect());
        	Alert.alert("§4&lWarning!", null, true, "§eYou are trying to join the server: §cnull", "§eThis server disallows the usage of NoobCraft", "§eBye");
        }
    }

    public IChatComponent getChatComponent()
    {
        return this.lineString;
    }

    public int getUpdatedCounter()
    {
        return this.updateCounterCreated;
    }

    public int getChatLineID()
    {
        return this.chatLineID;
    }
}
