package com.tust.school.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tust.school.res.consts.Contant;
import com.tust.school.res.domain.entity.ClaimVoucher;
import com.tust.school.res.domain.entity.ClaimVoucherItem;
import com.tust.school.res.domain.entity.DealRecord;
import com.tust.school.res.domain.entity.Teacher;
import com.tust.school.res.mapper.ClaimVoucherItemMapper;
import com.tust.school.res.mapper.ClaimVoucherMapper;
import com.tust.school.res.mapper.DealRecordMapper;
import com.tust.school.res.mapper.TeacherMapper;
import com.tust.school.res.service.ClaimVoucherService;
import com.tust.school.res.utils.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    @Autowired
    private ClaimVoucherMapper claimVoucherMapper;
    @Autowired
    private ClaimVoucherItemMapper claimVoucherItemMapper;
    @Autowired
    private DealRecordMapper dealRecordMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date()); //设置创建时间
//        claimVoucher.setNextDealSn(claimVoucher.getCreateSn()); //把待处理人设置为创建者
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.insert(claimVoucher);

        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemMapper.insert(item);
        }
    }

    public ClaimVoucher get(int id) {
        QueryWrapper<ClaimVoucherItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("claim_voucher_id", id);
        return claimVoucherMapper.selectById(id);
    }

    public List<ClaimVoucherItem> getItems(int cvid) {
        QueryWrapper<ClaimVoucherItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("claim_voucher_id", cvid);

        return claimVoucherItemMapper.selectList(queryWrapper);
    }

    public List<DealRecord> getRecords(int cvid) {
        QueryWrapper<DealRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("claim_voucher_id", cvid);
        return dealRecordMapper.selectList(queryWrapper);
    }

    public List<ClaimVoucher> getForSelf(Integer sn) {
        Teacher teacher = teacherMapper.selectById(sn);

        QueryWrapper<ClaimVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ClaimVoucher::getCreateSn, teacher.getNo());
        return claimVoucherMapper.selectList(queryWrapper);
    }

    public List<ClaimVoucher> getForDeal(Integer teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);

        QueryWrapper<ClaimVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ClaimVoucher::getCreateSn, teacher.getNo());
        return claimVoucherMapper.selectList(queryWrapper);
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
//        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemMapper.selectByClaimVoucher(claimVoucher.getId());
        for(ClaimVoucherItem old:olds){
            boolean isHave=false;
            for(ClaimVoucherItem item:items){
                if(item.getId()==old.getId()){
                    isHave=true;
                    break;
                }
            }
            if(!isHave){
                claimVoucherItemMapper.delete(old.getId());
            }
        }
        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            if(item.getId()!=null && item.getId()>0){
                claimVoucherItemMapper.update(item);
            }else{
                claimVoucherItemMapper.insert(item);
            }
        }

    }

    public void submit(int id) {
        QueryWrapper<ClaimVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("claim_voucher_id", id);
        ClaimVoucher claimVoucher = claimVoucherMapper.selectById(id);
        Teacher teacher = teacherMapper.selectById(claimVoucher.getCreateSn());

        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
//        claimVoucher.setNextDealSn(.selectByDepartmentAndPost(teacher.getDepartmentSn(),Contant.POST_FM).get(0).getSn());
        claimVoucherMapper.updateById(claimVoucher);

        //记录保存
        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(UserContextHelper.getUserId());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherMapper.selectById(dealRecord.getClaimVoucherId());
        Teacher teacher = teacherMapper.selectById(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        //审核通过
        if(dealRecord.getDealWay().equals(Contant.DEAL_PASS)){
            //报销单金额<5k不需要审核
            if(claimVoucher.getTotalAmount()<=Contant.LIMIT_CHECK){
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
//                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
//                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_BACK)){    //打回
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
//            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_REJECT)){  //拒绝
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
//            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_PAID)){    //打款
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
//            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }

        claimVoucherMapper.updateById(claimVoucher);
        dealRecordMapper.insert(dealRecord);
    }

}
