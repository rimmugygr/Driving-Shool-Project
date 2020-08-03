import {EmbeddedViewRef, Injectable, TemplateRef, ViewContainerRef} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private targets: Map<string, ViewContainerRef>;

  constructor() {
    this.targets = new Map<string, ViewContainerRef>();
  }

  addTarget(target: string, viewContainer: ViewContainerRef) {
    this.targets.set(target, viewContainer);
  }

  clearTarget(target: string) {
    this.getTarget(target)?.clear();
  }

  attach(target: string, template: TemplateRef<any>) {
    // const view = template.createEmbeddedView(null);
    // this.getTarget(target)?.insert(view);
    // view.detectChanges();

    // in one line above
   const view: EmbeddedViewRef<any> = this.getTarget(target)?.createEmbeddedView(template);
   view.checkNoChanges();
  }

  private getTarget(target: string): ViewContainerRef {
    return this.targets.has(target) ? this.targets.get(target) : null;
  }
}
