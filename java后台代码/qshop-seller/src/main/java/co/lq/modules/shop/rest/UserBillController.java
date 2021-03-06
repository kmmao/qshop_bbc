package co.lq.modules.shop.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.lq.aop.log.Log;
import co.lq.modules.security.security.vo.JwtUser;
import co.lq.modules.shop.service.UserBillService;
import co.lq.modules.shop.service.dto.UserBillQueryCriteria;
import co.lq.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author billy
 * @date 2019-11-06
 */
@Api(tags = "商城:用户账单管理")
@RestController
@RequestMapping("api")
public class UserBillController {

    private final UserBillService    userBillService;
    private final UserDetailsService userDetailsService;

    public UserBillController(UserBillService userBillService, UserDetailsService userDetailsService) {
        this.userBillService = userBillService;
        this.userDetailsService = userDetailsService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/userBill")
    @PreAuthorize("@el.check('admin','USERBILL_ALL','USERBILL_SELECT')")
    public ResponseEntity getUserBills(UserBillQueryCriteria criteria, Pageable pageable) {
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        criteria.setStoreId(jwtUser.getStoreId());
        return new ResponseEntity<>(userBillService.queryAll(criteria, pageable), HttpStatus.OK);
    }

}
