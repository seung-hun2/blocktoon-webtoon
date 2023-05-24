package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Demand;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DemandPort {

    void postWebtoonDemand(Demand demand, String type, String target) throws IOException;

    void removeWebtoonDemand(Demand demand);

    void postEpisodeDemand(Demand demand, String type, String target) throws IOException, ParseException;

    void removeEpisodeDemand(Demand demand);

    void checkPostWebtoonDemand(Demand demand, String type, String target, String whether);

    void checkRemoveWebtoonDemand(Demand demand, String whether);

    void checkPostEpisodeDemand(Demand demand, String type, String target, String whether) throws ParseException;

    void checkRemoveEpisodeDemand(Demand demand, String whether);

    List<Demand> getWebtoonDemand(Demand demand, String type) throws IOException;

    List<Demand> getEpisodeDemand(Demand demand, String type);

}
