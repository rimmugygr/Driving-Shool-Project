import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginModalComponent} from './commponent/login-modal/login-modal.component';
import {TopProfileComponent} from './commponent/top-profile/top-profile.component';
import {WhenDirective} from './directive/when.directive';
import {AddTargetDirective} from './directive/add-target.directive';
import {AttachToDirective} from './directive/attach-to.directive';
import {FooterComponent} from './layout/footer.component';
import {NavbarComponent} from './layout/navbar.component';
import {SidebarComponent} from './layout/sidebar.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [
    LoginModalComponent,
    TopProfileComponent,
    WhenDirective,
    AddTargetDirective,
    AttachToDirective,
    FooterComponent,
    NavbarComponent,
    SidebarComponent
  ],
  imports: [
    CommonModule,
    NgbModule,
    FlexLayoutModule,
    MatButtonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatButtonToggleModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    RouterModule
  ],
  exports: [
    LoginModalComponent,
    TopProfileComponent,
    WhenDirective,
    AddTargetDirective,
    AttachToDirective,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    CommonModule,
    NgbModule,
    FlexLayoutModule,
    MatButtonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatButtonToggleModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    RouterModule
  ]
})
export class SharedModule { }
