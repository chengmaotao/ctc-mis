package io.credittag.mis.modules.ctc.controller;

import java.util.Arrays;
import java.util.Map;

import io.credittag.mis.common.validator.ValidatorUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.service.WalletAssetService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 钱包统计、地址表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@RestController
@RequestMapping("ctc/walletasset")
public class WalletAssetController {
    @Autowired
    private WalletAssetService walletAssetService;

    
	@RequestMapping("/walletDataCount")
	@RequiresPermissions("ctc:walletasset:datacount")
	public R walletDataCount(@RequestParam Map<String, Object> params) {

		return walletAssetService.walletDataCount(params);

	}
	
	@RequestMapping("/walletDataCountTotal")
	@RequiresPermissions("ctc:walletasset:datacount")
	public R walletDataCountTotal(@RequestParam Map<String, Object> params) {

		return walletAssetService.walletDataCountTotal(params);

	}
	
    
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:walletasset:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = walletAssetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:walletasset:info")
    public R info(@PathVariable("id") Long id){
        WalletAssetEntity walletAsset = walletAssetService.selectById(id);

        return R.ok().put("walletAsset", walletAsset);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:walletasset:save")
    public R save(@RequestBody WalletAssetEntity walletAsset){
        walletAssetService.insert(walletAsset);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:walletasset:update")
    public R update(@RequestBody WalletAssetEntity walletAsset){
        ValidatorUtils.validateEntity(walletAsset);
        walletAssetService.updateAllColumnById(walletAsset);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:walletasset:delete")
    public R delete(@RequestBody Long[] ids){
        walletAssetService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    
    @RequestMapping("/rank")
   // @RequiresPermissions("ctc:walletasset:delete")
    public R rank(@RequestParam Map params){
    	return  walletAssetService.ranklist(params);
     
    }
    @RequestMapping("/rankdetail")
    // @RequiresPermissions("ctc:walletasset:delete")
     public R rankDetail(@RequestParam Map params){
     	return  walletAssetService.rankDetail(params);
      
     }

}
