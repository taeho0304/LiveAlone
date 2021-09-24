<template>
    <div>
        <div class="container">
            
            <tabs style="margin-top: 0px;" type="primary" tabContentClasses="tab-subcategories" centered="centered">
                <tab-pane>
                    <span slot="label" @click="click()">
                        <i class="now-ui-icons business_badge"></i>
                        일반회원
                    </span>
                    <div class="row ml-auto mr-auto">
                        <fg-input class="input col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkName()" placeholder="나혼족" v-model="user.userName" addon-left-icon="now-ui-icons users_circle-08"></fg-input>
                        <div v-if="!errors.requireName" class="check col-md-6 col-12">필수 정보입니다.</div>
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 20.7%; padding-bottom: 0px;" placeholder="alive" v-model="user.userId" @blur="checkID()" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 0 0 0 0;">
                            <n-button @click="clickDuplicate(user.userId)" size="sm" type="primary" style="margin-top: 5px;">확인</n-button>
                        </div>
                        <div class="check col-md-6 col-12" v-if="!errors.requireID">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxID">
                            최대 16자까지 입력 가능합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPass()" placeholder="alive123@" v-model="user.userPass" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePass" style="color:red;">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.minPass" style="color:red;">
                            최소 9 글자까지 입력 해야 합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxPass" style="color:red;">
                            최대 16 글자까지 입력 가능합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPass" style="color:red;">
                            비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="matchPass()" placeholder="alive123@" v-model="passwordcheck" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.checkPass" style="">비밀번호가 일치하지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkEmail()" placeholder="alive@alive.com" v-model="user.userEmail" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requireEmail">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchEmail">이메일 형식이 알맞지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPhone()" placeholder="010-1234-5678" v-model="user.userPhone" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePhone">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPhone">전화번호 형식이 알맞지 않습니다.</div>
                    </div>
                    <div class="col-md-2 col-6" style="float: none; margin:0 auto;">
                        <div class="text-center">
                            <a @click="clickRegisterMember()" class="btn btn-primary btn-round btn-lg btn-block">회원가입</a>
                        </div>
                    </div>
                </tab-pane>
                <tab-pane>
                    <span slot="label" @click="click()">
                        <i class="now-ui-icons business_badge"></i>
                        기업회원
                    </span>
                    <alert type="danger">기업회원일 경우 사업자 번호가 필요합니다!</alert>
                    <div class="row ml-auto mr-auto">
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 20.7%; padding-bottom: 0px;" @blur="checkNumber()" placeholder="11111-2222-33444" v-model="estateNumber" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 0;">
                            <n-button @click="clickCertification(estateNumber)" size="sm" type="primary" style="margin-top: 5px;">인증</n-button>
                        </div>
                        <div class="check col-md-6 col-12" v-if="!errors2.requireNumber">필수 정보입니다.</div>
                        <fg-input class="input col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkName2()" placeholder="나고독" v-model="estate.userName" addon-left-icon="now-ui-icons users_circle-08"></fg-input>
                        <div v-if="!errors2.requireName" class="check col-md-6 col-12">필수 정보입니다.</div>
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 20.7%; padding-bottom: 0px;" placeholder="alive" v-model="estate.userId" @blur="checkID2()" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 0;">
                            <n-button @click="clickDuplicate(estate.userId)" size="sm" type="primary" style="margin-top: 5px;">확인</n-button>
                        </div>
                        <div class="check col-md-6 col-12" v-if="!errors2.requireID">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.maxID">
                            최대 16자까지 입력 가능합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPass2()" placeholder="alive123@" v-model="estate.userPass" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors2.requirePass" style="color:red;">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.minPass" style="color:red;">
                            최소 9 글자까지 입력 해야 합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.maxPass" style="color:red;">
                            최대 16 글자까지 입력 가능합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.matchPass" style="color:red;">
                            비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="matchPass2()" placeholder="alive123@" v-model="passwordcheck" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors2.checkPass" style="">비밀번호가 일치하지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkEmail2()" placeholder="alive@alive.com" v-model="estate.userEmail" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors2.requireEmail">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.matchEmail">이메일 형식이 알맞지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPhone2()" placeholder="010-1234-5678" v-model="estate.userPhone" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors2.requirePhone">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors2.matchPhone">전화번호 형식이 알맞지 않습니다.</div>
                    </div>
                    <div class="col-md-2 col-6" style="float: none; margin:0 auto;">
                        <div class="text-center">
                            <a @click="clickRegisterEstate()" class="btn btn-primary btn-round btn-lg btn-block">회원가입</a>
                        </div>
                    </div>
                </tab-pane>
                <h6 style="text-align:center;">
                  <router-link to="/"><a class="link" style="color:#888888">메인으로</a></router-link>
                </h6>
            </tabs>
        </div>
    </div>
</template>
<script src="./user.js/signup.js">
</script>
<style scoped>
    .input .form-control,
    .form-group .el-input__inner,
    .el-date-picker .el-input .el-input__inner {
        background-color: #ffffff !important;
        border: 0 !important;
    }

element.style {
    margin-bottom: 0px !important;
    margin-top: 10px ;
}
    .check {
        text-align: center;
        float: none;
        margin: 0 auto;
        padding-top: 0;
        color: red;
        padding-bottom: 15px;
    }
</style>