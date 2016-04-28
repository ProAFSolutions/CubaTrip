package com.proafsolutions.cubatrip.ui.map;

import com.graphhopper.GHResponse;
import com.graphhopper.util.Helper;
import com.graphhopper.util.Instruction;
import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.UserSettings;
import com.proafsolutions.cubatrip.infrastructure.config.Constants;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;
import com.proafsolutions.cubatrip.ui.listener.NavigatorListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/27/2016.
 */
public class MapNavigator {

    private GHResponse ghResponse;

    private boolean on;
    private List<NavigatorListener> listeners;
    private static MapNavigator _instance = null;


    private MapNavigator() {
        this.ghResponse = null;
        this.on = false;
        this.listeners = new ArrayList<>();
    }

    public static MapNavigator getInstance() {
        if (_instance == null) {
            _instance = new MapNavigator();
        }
        return _instance;
    }

    public static void reset() {
        _instance = new MapNavigator();
    }

    public GHResponse getGhResponse() {
        return ghResponse;
    }

    public void setGhResponse(GHResponse ghResponse) {
        this.ghResponse = ghResponse;
        setOn(ghResponse != null);
    }

    public String getDistance(Instruction distance) {
        if (distance.getSign() == 4) return "";//finished
        double d = distance.getDistance();
        if (d < 1000) return Math.round(d) + " meter";
        return (((int) (d / 100)) / 10f) + " km";
    }


    public String getDistance() {
        if (getGhResponse() == null) return 0 + " km";
        double d = getGhResponse().getBest().getDistance();
        if (d < 1000) return Math.round(d) + " meter";
        return (((int) (d / 100)) / 10f) + " km";
    }


    public String getTime() {
        if (getGhResponse() == null) return " ";
        int t = Math.round(getGhResponse().getBest().getTime() / 60000);
        if (t < 60) return t + " min";
        return t / 60 + " h: " + t % 60 + " m";
    }


    public String getTime(Instruction time) {
        return Math.round(getGhResponse().getBest().getTime() / 60000) + " min";
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
        broadcast();
    }

    protected void broadcast() {
        for (NavigatorListener listener : listeners) {
            listener.statusChanged(isOn());
        }
    }

    public void addListener(NavigatorListener listener) {
        listeners.add(listener);
    }

    public String toString() {
        String s = "";
        if (ghResponse.getBest().getInstructions() != null) {
            for (Instruction i : ghResponse.getBest().getInstructions()) {
                s += "------>\ntime <long>: " + i.getTime() + "\n" + "name: street name" + i.getName() + "\n" +
                        "annotation <InstructionAnnotation>" +
                        i.getAnnotation() + "\n" + "distance" + i.getDistance() + "\n" + "sign <int>:" + i.getSign() +
                        "\n" + "Points <PointsList>: " + i.getPoints() + "\n";
            }
        }
        return s;
    }

    public int getTravelModeResId() {
        UserSettings settings = RepositoryProvider.getUserSettingsRepository().getSettings();
        switch (settings.getTravelMode()) {
            case Constants.MAP_TAVEL_MODE_FOOT:
                return R.drawable.ic_directions_walk_orange_24dp;
            case Constants.MAP_TAVEL_MODE_BIKE:
                return R.drawable.ic_directions_bike_orange_24dp;
            case Constants.MAP_TAVEL_MODE_CAR:
                return R.drawable.ic_directions_car_orange_24dp;
        }

        return 0;
    }


    public int getDirectionSign(Instruction itemData) {
        switch (itemData.getSign()) {
            case -6:
                return R.drawable.ic_roundabout;
            case -3:
                return R.drawable.ic_turn_sharp_left;
            case -2:
                return R.drawable.ic_turn_left;
            case -1:
                return R.drawable.ic_turn_slight_left;
            case 0:
                return R.drawable.ic_continue_on_street;
            case 1:
                return R.drawable.ic_turn_slight_right;
            case 2:
                return R.drawable.ic_turn_right;
            case 3:
                return R.drawable.ic_turn_sharp_right;
            case 4:
                return R.drawable.ic_finish_flag;
            case 5:
                return R.drawable.ic_reached_via;
            case 6:
                return R.drawable.ic_roundabout;
        }
        return -1;
    }


    public String getDirectionDescription(Instruction instruction) {
        if (instruction.getSign() == 4) return "Navigation End";//4
        String str;
        String streetName = instruction.getName();
        int sign = instruction.getSign();
        if (sign == Instruction.CONTINUE_ON_STREET) {//0
            str = Helper.isEmpty(streetName) ? "Continue" : ("Continue onto " + streetName);
        } else {
            String dir = "";
            switch (sign) {
                case Instruction.LEAVE_ROUNDABOUT://-6
                    dir = ("Leave roundabout");
                    break;
                case Instruction.TURN_SHARP_LEFT://-3
                    dir = ("Turn sharp left");
                    break;
                case Instruction.TURN_LEFT://-2
                    dir = ("Turn left");
                    break;
                case Instruction.TURN_SLIGHT_LEFT://-1
                    dir = ("Turn slight left");
                    break;
                case Instruction.TURN_SLIGHT_RIGHT://1
                    dir = ("Turn slight right");
                    break;
                case Instruction.TURN_RIGHT://2
                    dir = ("Turn right");
                    break;
                case Instruction.TURN_SHARP_RIGHT://3
                    dir = ("Turn sharp right");
                    break;
                case Instruction.REACHED_VIA://5
                    dir = ("Reached via");
                    break;
                case Instruction.USE_ROUNDABOUT://6
                    dir = ("Use roundabout");
                    break;
            }
            str = Helper.isEmpty(streetName) ? dir : (dir + " onto " + streetName);
        }
        return str;
    }

}
