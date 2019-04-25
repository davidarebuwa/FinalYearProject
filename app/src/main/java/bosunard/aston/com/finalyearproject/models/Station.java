package bosunard.aston.com.finalyearproject.models;

import android.net.Uri;

import java.util.ArrayList;

public class Station {



    public static class CrsCode {

        public String crsCode;

        public String getCrsCode() {
            return crsCode;
        }

        public void setCrsCode(String crsCode) {
            this.crsCode = crsCode;
        }
    }

    public static class Name {


        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class  Address{

        public static class PostalAddress {

            public static class LineAddress {

                public String line;

                public String getLine() {
                    return line;
                }

                public void setLine(String line) {
                    this.line = line;
                }
            }


            public ArrayList<LineAddress> lineAddress;

            public String postcode;




            public ArrayList<LineAddress> getLineAddress() {
                return lineAddress;
            }

            public void setLineAddress(ArrayList<LineAddress> lineAddress) {
                this.lineAddress = lineAddress;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

        }
    }

    public static class Longitude{

        public double longitude;

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class Latitude{

        public double latitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }

    public static class StationFacilities{

        public boolean seatedAreaAvailable;
        public boolean waitingRoomAvailable;
        public boolean toiletAvailable;
        public boolean babyChangeAvailable;

        public static class Telephones{

            public boolean isAvailable;
            public String usageType;

            public boolean isAvailable() {
                return isAvailable;
            }

            public void setAvailable(boolean available) {
                isAvailable = available;
            }

            public String getUsageType() {
                return usageType;
            }

            public void setUsageType(String usageType) {
                this.usageType = usageType;
            }
        }

        public static class Wifi {


            public boolean wifiAvailable;

            public boolean isWifiAvailable() {
                return wifiAvailable;
            }

            public void setWifiAvailable(boolean wifiAvailable) {
                this.wifiAvailable = wifiAvailable;
            }

            public boolean atmAvailable;


        }


        public boolean isSeatedAreaAvailable() {
            return seatedAreaAvailable;
        }

        public void setSeatedAreaAvailable(boolean seatedAreaAvailable) {
            this.seatedAreaAvailable = seatedAreaAvailable;
        }

        public boolean isWaitingRoomAvailable() {
            return waitingRoomAvailable;
        }

        public void setWaitingRoomAvailable(boolean waitingRoomAvailable) {
            this.waitingRoomAvailable = waitingRoomAvailable;
        }

        public boolean isToiletAvailable() {
            return toiletAvailable;
        }

        public void setToiletAvailable(boolean toiletAvailable) {
            this.toiletAvailable = toiletAvailable;
        }

        public boolean isBabyChangeAvailable() {
            return babyChangeAvailable;
        }

        public void setBabyChangeAvailable(boolean babyChangeAvailable) {
            this.babyChangeAvailable = babyChangeAvailable;
        }
    }

    public static class AccessibilityServices{

        public static class HelpLine{

            public static class contactDetails{

                public String note;
                public Uri url;

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public Uri getUrl() {
                    return url;
                }

                public void setUrl(Uri url) {
                    this.url = url;
                }
            }

        }

        public static class Open{

            public static class  DayAndTimeAvailabilty{

                public static class DayTypes{

                    public boolean isMondayToSunday;

                    public boolean isMondayToSunday() {
                        return isMondayToSunday;
                    }

                    public void setMondayToSunday(boolean mondayToSunday) {
                        isMondayToSunday = mondayToSunday;
                    }
                }
            }

            public static class OpeningHours {

                public static class OpenPeriod {

                    public String startTime;
                    public String endTime;

                    public String getStartTime() {
                        return startTime;
                    }

                    public void setStartTime(String startTime) {
                        this.startTime = startTime;
                    }

                    public String getEndTime() {
                        return endTime;
                    }

                    public void setEndTime(String endTime) {
                        this.endTime = endTime;
                    }
                }

            }
        }

        public static class StaffHelpAvailable{

            public static class Annotation{

                public static class Note{

                    public String noteText;


                    public String getNoteText() {
                        return noteText;
                    }

                    public void setNoteText(String noteText) {
                        this.noteText = noteText;
                    }
                }


            }

            public boolean isAvailable;

            public boolean isAvailable() {
                return isAvailable;
            }

            public void setAvailable(boolean available) {
                isAvailable = available;
            }
        }

        public static class  InductionLoop{

            public boolean isInductionLoopAvailable;

            public boolean isInductionLoopAvailable() {
                return isInductionLoopAvailable;
            }

            public void setInductionLoopAvailable(boolean inductionLoopAvailable) {
                isInductionLoopAvailable = inductionLoopAvailable;
            }
        }

        public static class AccessibleTicketMachines{

            public boolean isAccessibleTicketMachinesAvailable;

            public boolean isAccessibleTicketMachinesAvailable() {
                return isAccessibleTicketMachinesAvailable;
            }

            public void setAccessibleTicketMachinesAvailable(boolean accessibleTicketMachinesAvailable) {
                isAccessibleTicketMachinesAvailable = accessibleTicketMachinesAvailable;
            }
        }

        public static class HeightAdjustedTicketOfficeCounter{

            public boolean isHeightAdjustedTicketOfficeCounterAvailable;

            public boolean isHeightAdjustedTicketOfficeCounterAvailable() {
                return isHeightAdjustedTicketOfficeCounterAvailable;
            }

            public void setHeightAdjustedTicketOfficeCounterAvailable(boolean heightAdjustedTicketOfficeCounterAvailable) {
                isHeightAdjustedTicketOfficeCounterAvailable = heightAdjustedTicketOfficeCounterAvailable;
            }
        }

        public static class RampForTrainAccess{

            public boolean isRampForTrainAccessAvailable;

            public boolean isRampForTrainAccessAvailable() {
                return isRampForTrainAccessAvailable;
            }
        }

        public static class NearestStationsWithMoreFacilities{

            public String crsCode;

            public String getCrsCode() {
                return crsCode;
            }

            public void setCrsCode(String crsCode) {
                this.crsCode = crsCode;
            }
        }

        public static class NationalKeyToilets{

            public boolean isNationalKeyToiletsAvailable;

            public boolean isNationalKeyToiletsAvailable() {
                return isNationalKeyToiletsAvailable;
            }

            public void setNationalKeyToiletsAvailable(boolean nationalKeyToiletsAvailable) {
                isNationalKeyToiletsAvailable = nationalKeyToiletsAvailable;
            }
        }

        public static class StepFreeAccess{

            public static class Coverage{

                public String coverage;

                public String getCoverage() {
                    return coverage;
                }

                public void setCoverage(String coverage) {
                    this.coverage = coverage;
                }
            }

            public static class Annotation{

                public static class Note{

                    public String note;

                    public String getNote() {
                        return note;
                    }

                    public void setNote(String note) {
                        this.note = note;
                    }
                }
            }
        }

        public static class ImpairedMobilitySetDown{

            public boolean isImpairedMobilitySetDownAvailable;

            public boolean isImpairedMobilitySetDownAvailable() {
                return isImpairedMobilitySetDownAvailable;
            }

            public void setImpairedMobilitySetDownAvailable(boolean impairedMobilitySetDownAvailable) {
                isImpairedMobilitySetDownAvailable = impairedMobilitySetDownAvailable;
            }
        }


        public static class WheelchairsAvailable{

            public boolean isWheelchairsAvailable;

            public boolean isWheelchairsAvailable() {
                return isWheelchairsAvailable;
            }

            public void setWheelchairsAvailable(boolean wheelchairsAvailable) {
                isWheelchairsAvailable = wheelchairsAvailable;
            }
        }



    }


}
