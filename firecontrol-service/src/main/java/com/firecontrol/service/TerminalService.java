package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.TerminalEntity;
import jdk.nashorn.internal.ir.Terminal;

/**
 * Created by mariry on 2019/8/14.
 */
public interface TerminalService {

    public OpResult insertTerminal(TerminalEntity terminal);

    public OpResult updateTerminal(TerminalEntity terminal);

    public OpResult getTerminalList(TerminalEntity terminal, Integer currentPage, Integer length);

    public OpResult getTerminalIds(TerminalEntity terminal);

    public OpResult updateTerminalHardward(String terminalId, String hardwardId);


}
