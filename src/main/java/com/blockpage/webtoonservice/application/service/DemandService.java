package com.blockpage.webtoonservice.application.service;

import com.blockpage.webtoonservice.application.port.DemandDto;
import com.blockpage.webtoonservice.application.port.in.DemandUseCase;
import com.blockpage.webtoonservice.application.port.out.DemandPort;
import com.blockpage.webtoonservice.domain.Demand;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemandService implements DemandUseCase {

    private final DemandPort demandPort;

    @Override
    public void postDemand(DemandQuery demandQuery) throws IOException, ParseException {
        System.out.println("실행");
        switch (demandQuery.getTarget()) {
            case "webtoon":
                if ("enroll".equals(demandQuery.getType()) || "modify".equals(demandQuery.getType())) {
                    System.out.println("service webtoon enroll or modify");
                    Demand demand = Demand.toDomainFromWebtoon(demandQuery);
                    demandPort.postWebtoonDemand(demand, demandQuery.getType(), demandQuery.getTarget());

                } else if ("remove".equals(demandQuery.getType())) {
                    Demand demand = Demand.toDomainFromWebtoon(demandQuery);
                    demandPort.removeWebtoonDemand(demand);

                }
                break;
            case "episode":
                if ("enroll".equals(demandQuery.getType()) || "modify".equals(demandQuery.getType())) {
                    System.out.println("service episode enroll or modify");
                    Demand demand = Demand.toDomainFromEpisode(demandQuery);
                    demandPort.postEpisodeDemand(demand, demandQuery.getType(), demandQuery.getTarget());

                } else if ("remove".equals(demandQuery.getType())) {
                    Demand demand = Demand.toDomainFromEpisode(demandQuery);
                    demandPort.removeEpisodeDemand(demand);
                }
                break;
        }
    }

    @Override
    public void checkDemand(DemandQuery demandQuery) throws IOException, ParseException {

        switch (demandQuery.getTarget()) {
            case "webtoon":
                System.out.println("service checkDemand webtoon 수행 ");
                if ("modify".equals(demandQuery.getType())) {
                    Demand demand = Demand.toDomainFromWebtoon(demandQuery);
                    demandPort.checkPostWebtoonDemand(demand, demandQuery.getType(), demandQuery.getTarget(), demandQuery.getWhether());

                } else if ("remove".equals(demandQuery.getType())) {
                    Demand demand = Demand.toDomainFromWebtoon(demandQuery);
                    demandPort.checkRemoveWebtoonDemand(demand, demandQuery.getWhether());

                }
                break;
            case "episode":
                System.out.println("service checkDemand episode 수행");
                Demand demand = Demand.toDomainFromEpisode(demandQuery);
                demandPort.checkPostEpisodeDemand(demand, demandQuery.getType(), demandQuery.getTarget(), demandQuery.getWhether());
                break;
        }
    }

    @Override
    public List<DemandDto> getDemand(DemandQuery demandQuery) throws IOException {
        if (demandQuery.getTarget().equals("webtoon")) {
            Demand demand = Demand.toDomainFromGet(demandQuery);
            List<Demand> demands = demandPort.getWebtoonDemand(demand, demandQuery.getType(), demandQuery.getPageNo());

            return demands.stream().map(DemandDto::toDtoFromDomain).toList();

        } else if (demandQuery.getTarget().equals("episode")) {
            Demand demand = Demand.toDomainFromGet(demandQuery);
            System.out.println("demand = " + demand.getEpisodeTitle());
            List<Demand> demands =
                demandPort.getEpisodeDemand(demand, demandQuery.getType(), demandQuery.getPageNo()) != null ? demandPort.getEpisodeDemand(
                    demand, demandQuery.getType(), demandQuery.getPageNo()) : new ArrayList<>();

            return demands.stream().map(DemandDto::toDtoFromDomain).toList();
        }
        return null;
    }

    @Override
    public Integer findTotalSize(DemandQuery demandQuery) {
        return demandPort.findTotalSize(demandQuery.getTarget(), demandQuery.getType());
    }
}
