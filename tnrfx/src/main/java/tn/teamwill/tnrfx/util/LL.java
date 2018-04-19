package tn.teamwill.tnrfx.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import bsh.EvalError;
import tn.teamwill.tnrfx.model.UiTestDetails;

public class LL {
	public static void main(String[] args) throws EvalError, FileNotFoundException, IOException {
		List<UiTestDetails> uiTestDetails = Utilities.fromJSONtoUiTestDetails(Utilities.findIp(),
				"8d8335f2-624c-4a6b-a045-3ad8c187f704\n");

		System.out.println(uiTestDetails.size());
	}
}
