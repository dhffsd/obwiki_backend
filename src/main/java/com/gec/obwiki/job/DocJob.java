package com.gec.obwiki.job;

import com.gec.obwiki.service.IDocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DocJob {
    private static final Logger LOG = LoggerFactory.getLogger(DocJob.class);

    @Autowired
    private IDocService docService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void updateEbookInfo() {
        LOG.info("开始更新电子书信息...");
        docService.updateEbookInfo();
        LOG.info("电子书信息更新完成");
    }
} 