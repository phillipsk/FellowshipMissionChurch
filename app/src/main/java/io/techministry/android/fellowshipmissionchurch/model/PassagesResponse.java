package io.techministry.android.fellowshipmissionchurch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassagesResponse {

    @SerializedName("search") @Expose private Search search;
    @SerializedName("meta") @Expose private Meta meta;

    /**
     * @return The search
     */
    public Search getSearch() {
        return search;
    }

    /**
     * @param search The search
     */
    public void setSearch(Search search) {
        this.search = search;
    }

    /**
     * @return The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
