package com.example.zefeiqukuai.my.shoukuan.fragment.card;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.mvp.BaseFragment;
import com.example.base.utils.LogUtil;
import com.example.base.utils.SPUtil;
import com.example.common.CommonResource;
import com.example.zefeiqukuai.R;
import com.example.zefeiqukuai.bean.BankCardBean;
import com.example.zefeiqukuai.bean.EventBusBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class CardFragment extends BaseFragment<CardView, CardPresenter> implements CardView {
    @BindView(R.id.card_bank)
    EditText cardBank;
    @BindView(R.id.chonghzi_type_lin)
    RelativeLayout chonghziTypeLin;
    @BindView(R.id.chongzhi_type_name)
    TextView chongzhiTypeName;
    @BindView(R.id.zhifubao_name)
    EditText zhifubaoName;
    @BindView(R.id.card_bank_name)
    EditText cardBankName;
    @BindView(R.id.card_bank_kh)
    EditText cardBankKh;
    private String cardbank;
    private String zhifubaoname;
    private String cardbankname;
    private String cardBankkh;
    BankCardBean bankCardBean;

    @Override
    public int getLayoutId() {
        return R.layout.card_fragment;
    }

    @Override
    public void initData() {
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        presenter.getGathing(3);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShou(EventBusBean shoukuan) {
        if (CommonResource.SHOUKUAN.equals(shoukuan.getType())) {
            cardbank = cardBank.getText().toString();
            zhifubaoname = zhifubaoName.getText().toString();
            cardbankname = cardBankName.getText().toString();
            cardBankkh = cardBankKh.getText().toString();
            if (TextUtils.isEmpty(cardbank) || TextUtils.isEmpty(zhifubaoname) || TextUtils.isEmpty(cardbankname) || TextUtils.isEmpty(cardBankkh)) {
                Toast.makeText(getContext(), "输入内容不能为空.请检查", Toast.LENGTH_SHORT).show();
            } else {
                SPUtil.addParm("cardbank", cardbank);
                SPUtil.addParm("cardzhihang", zhifubaoname);
                SPUtil.addParm("cardbankname", cardbankname);
                SPUtil.addParm("cardBankkh", cardBankkh);
                EventBus.getDefault().post(new EventBusBean(CommonResource.SHOUKUANFANGSHI, "card"));
            }
        }
    }

    @Override
    public void initClick() {

    }

    @Override
    public CardView createView() {
        return this;
    }

    @Override
    public CardPresenter createPresenter() {
        return new CardPresenter(getContext());
    }

    @Override
    public void loadZfbData(BankCardBean bankCardBean) {
       this. bankCardBean =bankCardBean;
       if (TextUtils.isEmpty(bankCardBean.getBankname())){
           cardBank.setHint("请输入开户银行");
           zhifubaoName.setHint("请输入开户支行");
           cardBankName.setHint("请输入开户姓名");
           cardBankKh.setHint("请输入银行卡号");
       }else{
           cardBank.setText(bankCardBean.getBankname());
           zhifubaoName.setText(bankCardBean.getBranch());
           cardBankName.setText(bankCardBean.getCardname());
           cardBankKh.setText(bankCardBean.getCardno());
       }
    }
}
