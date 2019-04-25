package bosunard.aston.com.finalyearproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class HelpEmergencyDialogFragment extends DialogFragment {

    private String[] emergencyOptions = { "Yes", "No"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Emergency?")
                .setItems(emergencyOptions, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(which == 0){

                            dialPhoneNumber("999");
                        }else if (which == 1){
                            dialPhoneNumber("101");

                        }
                    }
                });
        return builder.create();
    }

    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
