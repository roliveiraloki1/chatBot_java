/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import wrapper.IGDBWrapper;
import wrapper.Parameters;
import wrapper.Version;

/**
 *
 * @author roliv
 */
public class APIRequest {

    private IGDBWrapper wrapper;
    private Parameters parameters;

    public APIRequest(String fields) {
        this.wrapper = new IGDBWrapper("c82e50bcdd7ac72a94dc3b6e8298420f", Version.STANDARD, false);
        this.parameters = new Parameters()
                .addFields(fields)
                .addOrder("published_at:desc");
    }
}
