package com.blockpage.webtoonservice.adaptor.web.view;

import java.util.List;
import lombok.Getter;

@Getter
public class DemandPageView {

    private List<DemandView> demandView;
    private Integer totalSize;

    public DemandPageView(List<DemandView> demandView, Integer totalSize) {

        this.totalSize = totalSize;
        this.demandView = demandView;
    }
}
