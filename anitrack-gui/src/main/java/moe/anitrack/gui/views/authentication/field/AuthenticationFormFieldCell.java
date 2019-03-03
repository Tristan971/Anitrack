package moe.anitrack.gui.views.authentication.field;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.model.components.listview.ComponentListCell;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class AuthenticationFormFieldCell extends ComponentListCell<AuthenticationField> {

    public AuthenticationFormFieldCell(EasyFxml easyFxml, AuthenticationFormFieldComponent component) {
        super(easyFxml, component);
    }

}
